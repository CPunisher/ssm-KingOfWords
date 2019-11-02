var webSocket;
var score = 0;
var startTime = 0;
var started = false;
var roomId;
var mistakes = new Array();
var MessageType = {"Start": 0, "PlayerChange": 1, "Answer": 2, "Distribute": 3, "PlayerScore": 4, "End": 5}

$(document).ready(function () {
    roomId = $("#roomIdHidden")[0].value;
    if (!roomId) {
        roomId = sessionStorage.getItem("roomId");
    }
    setInterval("updateTime()", 50);
    connect();
})

function saveMistakes() {
    $.ajax({
        type: 'POST',
        url: '/kow/saveMistakes',
        traditional: true,
        data: {
            mistakes: mistakes
        },
        success: function () {
            alert("保存错题成功!");
        }
    });
}

function answer(option) {
    if (started && webSocket) {
        webSocket.send(JSON.stringify({
            messageType: MessageType.Answer,
            roomId: roomId,
            data: {
                option: option,
                timestamp: new Date().getTime()
            }
        }));
    }
}

function start() {
    if (webSocket) {
        webSocket.send(JSON.stringify({
            messageType: MessageType.Start,
            roomId: roomId,
            data: {},
        }));
        mistakes = new Array();
    }
}

function connect() {
    if (webSocket) {
        webSocket.close();
    }
    webSocket = new WebSocket("ws://localhost:8080/kow/multiplayer/" + roomId);

    /**
     * @param ev.data
     * @param data.messageType
     */
    webSocket.onmessage = function (ev) {
        var data = JSON.parse(ev.data);
        switch (data.messageType) {
            case MessageType.PlayerChange:
                playerJoin(data);
                break;
            case MessageType.Answer:
                handleAnswerResult(data);
                break;
            case MessageType.Distribute:
                updateWord(data);
                break;
            case MessageType.PlayerScore:
                updateScore();
                updateScoreTable(data);
                break;
            case MessageType.End:
                endGame();
                break;
        }
    }

    webSocket.onopen = function (ev) {
        showMessage("连接成功");
    }

    webSocket.onclose = function (ev) {
        showMessage("连接关闭");
    }
}

function endGame() {
    var p = document.createElement("p");
    p.innerHTML =  "游戏结束! <a onclick='saveMistakes()' style='cursor: pointer; color: blue; text-decoration: underline'>保存错题</a>";
    document.getElementById("message").append(p);
    started = false;
}

/**
 * @param data.result
 * @param data.correctOption
 * @param data.option
 * @param data.wordId
 */
function handleAnswerResult(data) {
    if (data.result == true) {
        showAnswerResult("回答正确:" + $("#word")[0].innerText + " " + $("#option" + data.correctOption)[0].innerText, true);
        $("#option" + data.option)[0].style.backgroundColor = "green";
    } else {
        showAnswerResult("回答错误:" + $("#word")[0].innerText + " " + $("#option" + data.correctOption)[0].innerText, false);
        showAnswerResult("但是你选择的是:" + $("#option" + data.option)[0].innerText, false)
        mistakes[mistakes.length] = data.wordId;
        $("#option" + data.option)[0].style.backgroundColor = "red";
        $("#option" + data.correctOption)[0].style.backgroundColor = "green";
    }
}

/**
 * @param data.players
 */
function playerJoin(data) {
    for (var i = 0; i < data.players.length; i++) {
        showMessage(data.players[i]["nickname"] + ", " + data.players[i]["iconUrl"] + "加入游戏");
    }
}

/**
 * @param data.word
 * @param data.options
 * @param data.timestamp
 */
function updateWord(data) {
    startTime = new Date().getTime();
    started = true;
    $("#word")[0].innerText = data.word;
    for (var i = 0; i < 4; i++) {
        $("#option" + i)[0].style.backgroundColor = "chocolate";
        $("#option" + i)[0].innerText = data.options[i];
    }
}

/**
 * @param data.playerGameData
 */
function updateScoreTable(data) {
    $("#div_score_table")[0].innerHTML = "";
    for (var i = 0; i < data.playerGameData.length; i++) {
        var p = document.createElement("p");
        var span = document.createElement("span");
        span.style.color = "green";
        span.innerText = " (+" + data.playerGameData[i]["delta"] + ")";
        p.innerText = data.playerGameData[i]["nickname"] + ": " + data.playerGameData[i]["score"];
        p.append(span);
        $("#div_score_table")[0].append(p);
    }
}

function updateScore() {
    $("#score")[0].innerText = score;
}

function showAnswerResult(msg, result) {
    var p = document.createElement("p");
    p.style.color = result == true ? "green" : "red";
    p.innerText = msg;
    document.getElementById("message").append(p);
}

function showMessage(msg) {
    var p = document.createElement("p");
    p.innerText = msg;
    document.getElementById("message").append(p);
}

function updateTime() {
    if (started && startTime != 0) {
        var restTime = Math.round(10 - (new Date().getTime() - startTime) / 1000);
        $("#time")[0].innerText = restTime;
        if (restTime <= 1) {
            answer(0);
        }
    }
}

function clearMessage() {
    $("#message")[0].innerHTML = "";
}
