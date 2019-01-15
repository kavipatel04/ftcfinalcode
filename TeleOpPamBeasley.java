package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo; //When servo's are added import this
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
@TeleOp (name = "TeleOpPamBeasley", group = "Michael Scott")
public class TeleOpPamBeasley extends OpMode {
    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftBack;
    public DcMotor rightBack;
    public DcMotor lift;
    public Servo lights;
    //public Servo harvester;
    //public Servo teammarker;
    //public Servo harvesterdown;
    public void init()  {
        rightBack = hardwareMap.dcMotor.get("rightBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        lights = hardwareMap.servo.get("lights");
        //harvester = hardwareMap.servo.get("harvester");
        //teammarker = hardwareMap.servo.get("teammarker");
        //harvesterdown = hardwareMap.servo.get("harvesterdown");
        lift = hardwareMap.dcMotor.get("lift");
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        lights.setPosition(0.3);
        lift.setPower(0);****
        //robot.init
    }
    @Override
    public void loop(){
        double leftYvalue = gamepad1.left_stick_y;
        double rightYvalue = gamepad1.right_stick_y;
        boolean RightX = gamepad1.right_bumper;
        boolean LeftX = gamepad1.left_bumper;
        boolean Agodown = gamepad2.a;
        boolean Ygoup = gamepad2.y;
        // Adds data to the driver station
        telemetry.addData("The Left Joystick 'Y' Value", leftYvalue);
        telemetry.addData("The Right Joystick 'Y' Value", rightYvalue);
        telemetry.addData("The Left Bumper", LeftX);
        telemetry.addData("The Right Bumper", RightX);
        telemetry.addData("A", Agodown);
        telemetry.addData("Y", Ygoup);
        // Sets the min/max values
        leftYvalue = Range.clip(leftYvalue, -1, 1);
        rightYvalue = Range.clip(rightYvalue, -1, 1);
        // Stops arm from moving when A and Y aren't pressed
        if (Agodown == false){
            lift.setPower(0);
        }
        if (Ygoup == false) {
            lift.setPower(0);
        }
        // When A is pressed, Arm goes down, when Y is pressed, arm goes up
        if (Agodown == true){
            lift.setPower(0.5);
        }
        if (Ygoup == true) {
            lift.setPower(-0.8);
        }
        //idle
        if (leftYvalue == 0 && rightYvalue == 0) {
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);
        }
        //moving backwards
        if (leftYvalue < 0 && rightYvalue < 0) {
            leftFront.setPower(leftYvalue);
            leftBack.setPower(leftYvalue);
            rightFront.setPower(rightYvalue);
            rightBack.setPower(rightYvalue);
        }
        //Turns the robot left
        if ( leftYvalue == 0 && rightYvalue > 0) {
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(rightYvalue);
            rightBack.setPower(rightYvalue);
        }
        //turns right
        if (leftYvalue > 0 && rightYvalue == 0) {
            leftFront.setPower(leftYvalue);
            leftBack.setPower(leftYvalue);
            rightFront.setPower(0);
            rightBack.setPower(0);
        }
        //turn clockwise - rotate in place
        if (leftYvalue > 0 && rightYvalue < 0) {
            leftFront.setPower(leftYvalue);
            leftBack.setPower(leftYvalue);
            rightFront.setPower(rightYvalue);
            rightBack.setPower(rightYvalue);
        }
        //turn counterclockwise - rotate in place
        if (leftYvalue < 0 && rightYvalue > 0) {
            leftFront.setPower(leftYvalue);
            leftBack.setPower(leftYvalue);
            rightFront.setPower(rightYvalue);
            rightBack.setPower(rightYvalue);
        }
        //moves forward
        if (leftYvalue > 0 && rightYvalue > 0) {
            leftFront.setPower(leftYvalue);
            leftBack.setPower(leftYvalue);
            rightFront.setPower(rightYvalue);
            rightBack.setPower(rightYvalue);
        }
        // Strafes Right
        if (RightX == true){
            leftFront.setPower(-0.75);
            leftBack.setPower(0.7);
            rightFront.setPower(0.7);
            rightBack.setPower(-0.7);
        }
        //turns right
        if (leftYvalue < 0 && rightYvalue == 0) {
            leftFront.setPower(leftYvalue);
            leftBack.setPower(leftYvalue);
            rightFront.setPower(0);
            rightBack.setPower(0);
        }
        //turns left
        if (leftYvalue == 0 && rightYvalue < 0) {
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(rightYvalue);
            rightBack.setPower(rightYvalue);
        }
        // Strafes left
        if (LeftX == true) {
            leftFront.setPower(0.7);
            leftBack.setPower(-0.7);
            rightFront.setPower(-0.75);
            rightBack.setPower(0.7);
        }
        //New Stuff Ends
        telemetry.update();
    }
}