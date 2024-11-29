"use strict";

// board
let board = document.getElementById("board");
let boardWidth = 500;
let boardHeight = 700;
board.width = boardWidth;
board.height = boardHeight;

let context = board.getContext("2d");

// car image and properties
let carChangeArr = ["Car1.png", "Car2.png", "Car3.png", "Car4.png", "Car5.png", "Car6.png", "Car7.png", "Car8.png", "Car9.png"];
let carImages = [];
let carPositions = [
    { x: 76, y: -120 },
    { x: 170, y: -120 },
    { x: 273, y: -120 },
    { x: 370, y: -120 }
];

let carSpeeds = [];
let carWidth = 55;
let carHeight = 80;
let initialY = -120;

// player car
let playerCarImage = new Image();
playerCarImage.src = "playerCar.png";
let playerCarWidth = carWidth;
let playerCarHeight = carHeight;
let playerCarPositionY = 580;

let playerCarJump = [76, 170, 273, 370];
let jump = 0;

//
let carCollisionPoint = playerCarPositionY - playerCarHeight;

function initializeCars() {
    for (let i = 0; i < carPositions.length; i++) {
        let carImage = new Image();
        carImage.src = carChangeArr[i % carChangeArr.length];
        carImages.push(carImage);
        carSpeeds.push(Math.floor(Math.random() * 6) + 2);
    }
}

// draw each car
function drawCar(carImage, x, y) {
    context.drawImage(carImage, x, y, carWidth, carHeight);
    context.drawImage(playerCarImage, playerCarJump[jump], playerCarPositionY, carWidth, carHeight);
}

function getDistance(x1, y1) {
    let x2 = playerCarJump[jump];
    let y2 = playerCarPositionY;

    let distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    if (distance <= 80) {
        context.font = "50px Arial";
        context.strokeStyle = "black";
        context.lineWidth = 4;
        context.strokeText("Game Over", 140, 100);
        for (let i = 0; i < carSpeeds.length; i++) {
            carSpeeds[i] = 0;
        }
        updateRight = function () {};
        updateLeft = function () {};
    }
}

// update all cars’ positions
function updateAllCars() {
    context.clearRect(0, 0, boardWidth, boardHeight);
    for (let i = 0; i < carPositions.length; i++) {
        let car = carPositions[i];

        // update car position
        car.y += carSpeeds[i];

        // car collision condition
        if ((car.y) >= carCollisionPoint) {
            let x = car.x;
            let y = car.y;
            getDistance(x, y);
        }

        // draw cars
        drawCar(carImages[i], car.x, car.y);

        if (car.y > boardHeight) {
            car.y = initialY;
            carSpeeds[i] = Math.floor(Math.random() * 6) + 2;
            carImages[i].src = carChangeArr[Math.floor(Math.random() * carChangeArr.length)];
        }
    }

    requestAnimationFrame(updateAllCars);
}

function updateRight() {
    if (playerCarJump.length - 1 != jump) {
        jump++;
        context.drawImage(playerCarImage, playerCarJump[jump], playerCarPositionY, carWidth, carHeight);
    }
}
function updateLeft() {
    if (jump > 0) {
        jump--;
        context.drawImage(playerCarImage, playerCarJump[jump], playerCarPositionY, carWidth, carHeight);
    }
}

function resetGame() {
    carPositions = [
        { x: 76, y: initialY },
        { x: 170, y: initialY },
        { x: 273, y: initialY },
        { x: 370, y: initialY }
    ];

    jump = 0;

    carImages = [];
    carSpeeds = []; 
    initializeCars();

    updateRight = function () {
        if (playerCarJump.length - 1 != jump) {
            jump++;
            context.drawImage(playerCarImage, playerCarJump[jump], playerCarPositionY, carWidth, carHeight);
        }
    };

    updateLeft = function () {
        if (jump > 0) {
            jump--;
            context.drawImage(playerCarImage, playerCarJump[jump], playerCarPositionY, carWidth, carHeight);
        }
    };

    context.clearRect(0, 0, boardWidth, boardHeight);
    requestAnimationFrame(updateAllCars);
}

document.addEventListener("keydown", function (e) {
    if (e.code === "ArrowLeft") {
        updateLeft();
    }
    if (e.code === "ArrowRight") {
        updateRight();
    }
    if(e.code === "Enter") {
        resetGame();
        
    }
});

initializeCars();
updateAllCars();

