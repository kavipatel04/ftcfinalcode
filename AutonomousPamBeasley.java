package org.firstinspires.ftc.teamcode;
//Kavi [10:35 AM]

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspire.ftc.teamcode.HardwarePamBeasley;

@Autonomous(name = "AutonomousPamBeasley", group = "official")
public class AutonomousPamBeasley extends LinearOpMode {
    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;
    public ElapsedTime runtime = new ElapsedTime(); //sets up a timer in the program

    public DcMotor lift;
    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftBack;
    public DcMotor rightBack;
    public Servo teammarker;
    public Servo motordown;

    @Override
    public void runOpMode() {
        //*****INITIALIZE*****//
        telemetry.addData("Initialiing...", "Please be patient");
        telemetry.update();
        rightBack = hardwareMap.dcMotor.get("rightBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        teammarker = hardwareMap.servo.get("teammarker");
        motordown = hardwareMap.servo.get("motordown");
        lift = hardwareMap.dcMotor.get("lift");
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);


        //reset all motor encoders
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Finished Initializing!", "Go team!");
        telemetry.update();
        //*****START*****//
        waitForStart();
        telemetry.addData("Starting", "GoGOGO");
        telemetry.update();
        runtime.reset();
        lift.setPower(0);

        encoderDrive(.3, -4, -4,4);
        teammarker.setPosition(120);
        encoderDrive(.3, -4, 4, 5 );

    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
        double timeoutS) {
            int newLeftTarget;
            int newRightTarget;

        // Ensure that the opmode is still active

        // Determine new target position, and pass to motor controller

        newLeftTarget = leftBack.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
        newRightTarget = rightBack.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
        leftBack.setTargetPosition(newLeftTarget);
        leftFront.setTargetPosition(newLeftTarget);
        rightBack.setTargetPosition(newRightTarget);
        rightFront.setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        leftFront.setPower(Math.abs(speed));
        leftBack.setPower(Math.abs(speed));
        rightFront.setPower(Math.abs(speed));
        rightBack.setPower(Math.abs(speed));

        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        while (opModeIsActive() &&
                (runtime.seconds() < timeoutS) &&
                (leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy())) {

            // Display it for the driver.
            telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
            telemetry.addData("Path2", "Running at %7d :%7d",
                    leftFront.getCurrentPosition(),
                    rightFront.getCurrentPosition());
            leftBack.getCurrentPosition();
            rightBack.getCurrentPosition();
            telemetry.update();
        }

        // Stop all motion;
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

        // Turn off RUN_TO_POSITION
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //  sleep(250);   // optional pause after each move
    }
}






















































/**

//        if (mRunTime.time() == 0) {
//            telemetry.addData("timer reset", "YES");
//        }
//        // Reset the game clock to zero in Start()
//        mRunTime.reset();

 * telemetry.addData("Time: ", mRunTime.time());
 * telemetry.update();
 * //UNHOOK
 * if (mRunTime.time() > 0 && mRunTime.time() < 3 ) {
 * motordown.setPosition(75);
 * }
 * if(mRunTime.time() > 3 && mRunTime.time() < 6) {
 * lift.setPower(-.2);
 * }
 * // Check for 4 seconds elapsed in loop();
 * if (mRunTime.time() < 6) {
 * leftFront.setPower(-0.3);
 * leftBack.setPower(-0.3);
 * rightFront.setPower(-0.3);
 * rightBack.setPower(-0.3);
 * }
 * // Stop arm from moving and makes robot move after 2 seconds elapsed
 * if (mRunTime.time() == 6) {
 * lift.setPower(0);
 * }
 * if (mRunTime.time() > 6 && mRunTime.time() < 9) {
 * leftFront.setPower(0);
 * leftBack.setPower(0);
 * rightFront.setPower(0);
 * rightBack.setPower(0);
 * lift.setPower(.4);
 * }
 * if (mRunTime.time() > 9 && mRunTime.time() <10) {
 * //teammarker.setPosition(120);
 * leftFront.setPower(0.4);
 * leftBack.setPower(0.4);
 * rightFront.setPower(-0.4);
 * rightBack.setPower(-0.4);
 * lift.setPower(0);
 * }
 * if (mRunTime.time() > 10 && mRunTime.time() < 11) {
 * leftFront.setPower(0.3);
 * leftBack.setPower(0.3);
 * rightFront.setPower(0.3);
 * rightBack.setPower(0.3);
 * }
 * if (mRunTime.time() > 11 && mRunTime.time() < 12 ) {
 * leftFront.setPower(0);
 * leftBack.setPower(0);
 * rightFront.setPower(0.2);
 * rightBack.setPower(0.2);
 * }
 * if (mRunTime.time() > 12 && mRunTime.time() < 13) {
 * leftFront.setPower(0.3);
 * leftBack.setPower(0.3);
 * rightFront.setPower(0.3);
 * rightBack.setPower(0.3);
 * }
 * if (mRunTime.time() > 13 && mRunTime.time() < 15) {
 * leftFront.setPower(0);
 * leftBack.setPower(0);
 * rightFront.setPower(0);
 * rightBack.setPower(0);
 * teammarker.setPosition(120);
 * }
 * }
 * <p>
 * <p>
 * <p>
 * <p>
 *
**/
