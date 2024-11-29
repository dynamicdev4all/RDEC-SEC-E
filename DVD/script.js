"use strict";

// board
let board = document.getElementById("board");
let boardWidth = 800;
let boardHeight = 650;
let context = board.getContext("2d");

board.width = boardWidth;
board.height = boardHeight;



class Circle {
    constructor(ballPositionX, ballPositionY, ballRadius, ballSpeed, ballColor, rodX, rodY, rodWidth, rodHeight, text) {
        this.ballPositionX = ballPositionX;
        this.ballPositionY = ballPositionY;
        this.ballRadius = ballRadius;
        this.ballSpeed = ballSpeed;
        this.ballColor = ballColor;
        this.dx = 1 * this.ballSpeed;
        this.dy = 1 * this.ballSpeed;
        this.rodX = rodX;
        this.rodY = rodY;
        this.rodWidth = rodWidth;
        this.rodHeight = rodHeight;
        this.text = text;
    }

    draw() {
        context.beginPath();
        context.lineWidth = 5;
        context.textAlign = "center";
        context.textBaseline = "middle";
        context.font = "20px Arial";
        context.fillText(this.text, this.ballPositionX, this.ballPositionY);
        context.arc(this.ballPositionX, this.ballPositionY, this.ballRadius, 0, Math.PI * 2, false);
        context.stroke();

        context.fillRect(this.rodX, this.rodY, this.rodWidth, this.rodHeight);
        context.closePath();
    }

    update() {
        context.clearRect(0, 0, boardWidth, boardHeight);
        this.draw(context);

        if ((this.ballPositionX + this.ballRadius) > boardWidth) {
            this.dx = -this.dx;
        }
        if ((this.ballPositionX - this.ballRadius) < 0) {
            this.dx = -this.dx;
        }

        if ((this.ballPositionY - this.ballRadius) < 0) {
            this.dy = -this.dy;
        }

        if ((this.ballPositionY + this.ballRadius) >= collisionPoint) {
            let ballX = this.ballPositionX;
            let rod1X = this.rodX;
            let rod2X = this.rodX + this.rodWidth;

            if ((ballX > rod1X) && (ballX <= rod2X)) {
                this.dy = -this.dy;
                this.text++;
            }
            else {
                this.ballSpeed = 0;
                context.arc(this.ballPositionX, this.ballPositionY, this.ballRadius, 0, Math.PI * 2, false);
                context.font = "50px Arial";
                context.strokeStyle = "black";
                context.lineWidth = 4;
                context.strokeText("Game Over", 140, 100);
                return;
            }
        }

        this.ballPositionX = this.ballPositionX + this.dx;
        this.ballPositionY = this.ballPositionY + this.dy;
    }

    moveLeft() {
        if (this.rodX > rodMoveLeft) {
            this.rodX = this.rodX - move;
            context.fillRect(this.rodX, this.rodY, this.rodWidth, this.rodHeight);
        }
        if(this.rodX>0 && this.rodX<=move) {
            this.rodX = 0;
            context.fillRect(this.rodX, this.rodY, this.rodWidth, this.rodHeight);
        }
    }

    moveRight() {
        if (this.rodX < rodMoveRight) {
            this.rodX = this.rodX + move;
            context.fillRect(this.rodX, this.rodY, this.rodWidth, this.rodHeight);
        }
        if(this.rodX>=rodMoveRight && this.rodX<=600) {
            this.rodX = 600;
            context.fillRect(this.rodX, this.rodY, this.rodWidth, this.rodHeight);
        }
    }
}

let myCircle = new Circle(50, 50, 50, 5, "red", 300, 630, 200, 20, 0);
myCircle.draw();

// move conditions
let move = 30;
let rodMoveLeft = (boardWidth + move) - boardWidth;
let rodMoveRight = boardWidth - move - myCircle.rodWidth;
console.log(rodMoveRight);

let collisionPoint = boardHeight - myCircle.rodHeight;
console.log(collisionPoint);

function updateCircle() {
    requestAnimationFrame(updateCircle);
    myCircle.update();
}

updateCircle();

document.addEventListener("keydown", function (e) {
    if (e.code === "ArrowLeft") {
        myCircle.moveLeft();
    }
    if (e.code === "ArrowRight") {
        myCircle.moveRight();
    }
})














































