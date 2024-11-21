"use strict";

// board
let board;
let boardWidth = 1000;
let boardHeight = 640;
let context;

// bird
let birdWidth = 34;
let birdHeight = 24;
let birdX = boardWidth / 8;
let birdY = boardHeight / 2;
let birdImage;

let bird = {
    x: birdX,
    y: birdY,
    width: birdWidth,
    height: birdHeight
}

// pipe
let pipeArray = [];
let pipeWidth = 64;
let pipeHeight = 512;
let pipeX = boardWidth;
let pipeY = 0;
let topPipeImage;
let bottomPipeImage;

// game concepts
let velocityX = -2;
let velocityY = 0;
let gravity = 0.4;

let gameOver = false;
let score = 0;
let MaxScore = 0;

window.onload = function () {
    board = document.getElementById("board");
    context = board.getContext("2d");
    board.width = boardWidth;
    board.height = boardHeight;

    // draw bird
    birdImage = new Image();
    birdImage.src = "flappybird.png";
    birdImage.onload = function () {
        context.drawImage(birdImage, bird.x, bird.y, bird.width, bird.height);
    }

    topPipeImage = new Image();
    topPipeImage.src = "toppipe.png";
    bottomPipeImage = new Image();
    bottomPipeImage.src = "bottompipe.png";

    requestAnimationFrame(update);
    setInterval(placePipes, 1500);
    document.addEventListener("keydown", jumpBird);
}

function update() {
    requestAnimationFrame(update);
    if (gameOver) {
        
        return;
    }
    context.clearRect(0, 0, boardWidth, boardHeight);

    velocityY += gravity;
    bird.y = Math.max(bird.y + velocityY, 0);

    if ((bird.y + bird.height) > boardHeight) {
        gameOver = true;
    }

    context.drawImage(birdImage, bird.x, bird.y, bird.width, bird.height);

    for (let i = 0; i < pipeArray.length; i++) {
        let pipe = pipeArray[i];
        pipe.x += velocityX;
        context.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height);

        if (!pipe.passed && bird.x > (pipe.x + pipe.width)) {
            score = score + 0.5;
            pipe.passed = true;
        }

        if (detectCollision(bird, pipe)) {
            gameOver = true;
        }
    }

    // clear Array when it goes to the outside of the canvas
    while (pipeArray.length > 0 && pipeArray[0] < -pipeWidth) {
        pipeArray.shift();  // it removes the first element of an array
    }

    // set score
    context.font = "50px Arial";
    context.strokeStyle = "white";
    context.lineWidth = 2;
    context.strokeText(`Score:${score}`, 50, 50);

    // set MaxScore
    context.font = "20px Arial";
    context.strokeStyle = "white";
    context.lineWidth = 1;
    context.strokeText(`MaxScore:${MaxScore}`, 50, 80);


    if (gameOver) {
        context.font = "70px Arial";
        context.strokeStyle = "black";
        context.lineWidth = 5;
        context.strokeText(`GAME OVER`, 350, 200);
    }
}

function placePipes() {
    let randomPipeY = pipeY - (pipeHeight / 4) - (Math.random() * pipeHeight / 2);
    let openingSpace = boardHeight / 4;

    let topPipe = {
        img: topPipeImage,
        x: pipeX,
        y: randomPipeY,
        width: pipeWidth,
        height: pipeHeight,
        passed: false
    }
    pipeArray.push(topPipe);

    let bottomPipe = {
        img: bottomPipeImage,
        x: pipeX,
        y: randomPipeY + pipeHeight + openingSpace,
        width: pipeWidth,
        height: pipeHeight,
        passed: false
    }
    pipeArray.push(bottomPipe);
}

function jumpBird(e) {
    if (e.code === "ArrowUp" || e.code === "Space" || e.code === "KeyX") {
        velocityY = -6;
        if (gameOver) {
            if (score > MaxScore) {
                MaxScore = score;
                context.strokeText(`MaxScore:${MaxScore}`, 50, 80);
            }
            bird.y = birdY;
            pipeArray = [];
            score = 0;
            gameOver = false;
        }
    }
}

function detectCollision(bird, pipe) {
    return (bird.x + bird.width) > pipe.x &&
        bird.x < (pipe.x + pipe.width) &&
        bird.y < (pipe.y + pipe.height) &&
        (bird.y + bird.height) > pipe.y;
}


