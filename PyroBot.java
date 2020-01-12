package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//REV Expansion Hub Configuration
//0-frontLeft ( front left motor)
//1-backLeft ( back left motor)
//2-backRight (back right motor)
//3-frontRight (front right motor)
public class PyroBot {
    //motors are declared
    DcMotor frontRight = null;
    DcMotor backRight = null;
    DcMotor frontLeft = null;
    DcMotor backLeft = null;
    HardwareMap hwMap = null;
    // the motor tick count is a constant
    static final double MOTOR_TICK_COUNTS= 537.6;

    public void init(HardwareMap hwMap){
        // this is the hardware for the motors used and the robot will look for these motors in the configuration
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        backRight = hwMap.get(DcMotor.class, "backRight");
        // the polarity of the motors is set
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        // the power of the motors are set to zero
        PowerOff();
        // this sets the power of the motors zero at the start of a program
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // the motor encoders will be used
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        PowerOff();


    }

    public void PowerOff(){  // this method sets the power of the driving motors to zero
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

    }

    public void moveForward(double power, double distance){ //power from -1 to 1 and distance measured in inches
        // the motor encoders will be reset
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        double circumference = 3.14*3.937; //pi*diameter
        double rotationsrequired = distance/circumference; //inputed distance divided by the circumference of the mecanum wheels
        int encoderTargetPOS = (int)(rotationsrequired*537.6); // rotations needed * ticks per count on the gobilda motor
        //the encoder target position which was calculated above is input into the motors below
        frontLeft.setTargetPosition(encoderTargetPOS);
        backLeft.setTargetPosition(encoderTargetPOS);
        frontRight.setTargetPosition(encoderTargetPOS);
        backRight.setTargetPosition(encoderTargetPOS);


        // sets the motors to the respective power that was input
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
        //the mode is set to run to the target position that was set above
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(frontLeft.isBusy() || backRight.isBusy() || frontRight.isBusy() || backLeft.isBusy()){
            //wait for the motors to reach the target position

        }

        PowerOff();//the motors power is set to zero 


    }






}
