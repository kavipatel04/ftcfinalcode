package org.firstinspires.ftc.teamcode;

//Kavi [10:35 AM]

//hello my name is bob

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;





@Autonomous(name = "AutonomousPamBeasley", group = "official")
public class AutonomousPamBeasley extends OpMode {

    public ElapsedTime mRunTime = new ElapsedTime(); //sets up a timer in the program
    public DcMotor lift;
    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftBack;
    public DcMotor rightBack;
    public Servo teammarker;

    public void init() {
        lift = hardwareMap.dcMotor.get("lift");
        lift.setPower(0);
        rightBack = hardwareMap.dcMotor.get("rightBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        teammarker = hardwareMap.servo.get("teammarker");
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
    }


    public void start() {
        mRunTime.reset();
    }

    @Override
    public void loop() {


//        if (mRunTime.time() == 0) {
//            telemetry.addData("timer reset", "YES");
//        }
//        // Reset the game clock to zero in Start()
//        mRunTime.reset();
        telemetry.addData("Time: ", mRunTime.time());
        telemetry.update();

        //UNHOOK
        if (mRunTime.time() > 0 && mRunTime.time() < 6 ) {
            lift.setPower(-.2);
        }

        // Check for 4 seconds elapsed in loop();
        if (mRunTime.time() < 6) {
            leftFront.setPower(-0.3);
            leftBack.setPower(-0.3);
            rightFront.setPower(-0.3);
            rightBack.setPower(-0.3);
        }

        // Stop arm from moving and makes robot move after 2 seconds elapsed
        if (mRunTime.time() == 6) {
            lift.setPower(0);


        }
        if (mRunTime.time() > 6 && mRunTime.time() < 9) {
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);
            lift.setPower(.4);
        }
        if (mRunTime.time() > 9 && mRunTime.time() <10) {
            //teammarker.setPosition(120);
            leftFront.setPower(0.4);
            leftBack.setPower(0.4);
            rightFront.setPower(-0.4);
            rightBack.setPower(-0.4);
            lift.setPower(0);

        }

        if (mRunTime.time() > 10 && mRunTime.time() < 11) {

            leftFront.setPower(0.3);
            leftBack.setPower(0.3);
            rightFront.setPower(0.3);
            rightBack.setPower(0.3);
        }
        if (mRunTime.time() > 11 && mRunTime.time() < 12 ) {

            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0.2);
            rightBack.setPower(0.2);
        }
        if (mRunTime.time() > 12 && mRunTime.time() < 13) {
            leftFront.setPower(0.3);
            leftBack.setPower(0.3);
            rightFront.setPower(0.3);
            rightBack.setPower(0.3);

        }
        if (mRunTime.time() > 13 && mRunTime.time() < 15) {
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);
            teammarker.setPosition(120);
        }


    }

}
