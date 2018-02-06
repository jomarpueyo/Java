package jomarpueyo.myfirstapp;
/* Jomar Pueyo jomarpueyo@gmail.com
   RC Controller App using Bluetooth */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.content.Intent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //Initialize Variables
    BluetoothAdapter myBluetooth;
    Button btnf, btnb, btnl, btnr, bluetoothDiscover, exit;
    CheckBox checkBox;
    BluetoothSocket btSocket = null;
    UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");    //UUID to connect to bluetooth dongle
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variable values
        checkBox = (CheckBox) findViewById(R.id.checkBox);                  //Enable Bluetooth on device
        btnf = (Button) findViewById(R.id.button2);                         //Forward variable button linked to button id: button2
        btnl = (Button) findViewById(R.id.button3);                         //Left variable button linked to button id: button3
        btnr = (Button) findViewById(R.id.button4);                         //Right variable button linked to button id: button4
        btnb = (Button) findViewById(R.id.button);                          //Backward variable button linked to button id: button
        exit = (Button) findViewById(R.id.button5);                         //Button to close the program
        myBluetooth = BluetoothAdapter.getDefaultAdapter();                 //Bluetooth status
        bluetoothDiscover = (Button) findViewById(R.id.raspberryPi);        //Button Connect through bluetooth Pairing


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //This method checks if device supports Bluetooth
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { //Turn on device bluetooth
                if (isChecked) {
                    //Bluetooth Toggle enabled
                    if (myBluetooth == null) {
                        //Bluetooth not available on this device
                    }
                    if (!myBluetooth.isEnabled()) {
                        //Asks user to enable bluetooth
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, 1);
                    }
                }
                else {
                    //Bluetooth Toggle disabled
                    if (myBluetooth.isEnabled()) {
                        try {
                            //Close the bluetooth socket before disabling bluetooth
                            btSocket.close();
                        }
                        catch (IOException closeException) {
                            //Can't close btSocket
                            //Error Log here to console
                        }
                        myBluetooth.disable();
                    }
                }
            }
        });

        //Connectivity to Raspberry Pi using MAC address of bluetooth dongle
        bluetoothDiscover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Known MAC address of specific bluetooth dongle
                BluetoothDevice raspPi = myBluetooth.getRemoteDevice("98:D3:31:FB:67:B2");
                try {
                    btSocket = raspPi.createRfcommSocketToServiceRecord(BTMODULEUUID);
                    Toast.makeText(getBaseContext(), "Creating connection...", Toast.LENGTH_LONG).show();
                }
                catch (IOException e) {
                    //Error catch message
                    Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
                }
                try {
                    //Create connection
                    btSocket.connect();
                    Toast.makeText(getBaseContext(), "Connected!", Toast.LENGTH_LONG).show();
                }
                catch (IOException connectException) {
                    Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
                    try {
                        btSocket.close();
                    }
                    catch (IOException closeException) {
                        //Can't close btSocket
                        //Error Log here to console
                    }
                    return;
                }
            }
        });

        //Assigning variable buttons to their respective button widget
        btnf.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Bluetooth Send Forward action;
                    String msg = "f";
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write(msg.getBytes());
                        Toast.makeText(getBaseContext(), "Sending Forward...", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Forward failed", Toast.LENGTH_LONG).show();
                    }


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Bluetooth Send End action;
                    String msg = "s";
                    try {
                        outputStream.write(msg.getBytes());
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Stop failed", Toast.LENGTH_LONG).show();
                    }

                }
                // TODO Sends Bluetooth message to move forward end on release
                return false;
            }
        });

        btnl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Bluetooth Send Left action;
                    String msg = "l";
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write(msg.getBytes());
                        Toast.makeText(getBaseContext(), "Sending Left...", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Left failed", Toast.LENGTH_LONG).show();
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Bluetooth Send End action;
                    String msg = "s";
                    try {
                        outputStream.write(msg.getBytes());
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Stop failed", Toast.LENGTH_LONG).show();
                    }

                }
                // TODO Sends Bluetooth message to move forward end on release
                return false;
            }
        });

        btnr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Bluetooth Send Right action;
                    String msg = "r";
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write(msg.getBytes());
                        Toast.makeText(getBaseContext(), "Sending Right...", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Right failed", Toast.LENGTH_LONG).show();
                    }


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Bluetooth Send End action;
                    String msg = "s";
                    try {
                        outputStream.write(msg.getBytes());
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Stop failed", Toast.LENGTH_LONG).show();
                    }

                }
                // TODO Sends Bluetooth message to move forward end on release
                return false;
            }
        });

        btnb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Bluetooth Send Back action;
                    String msg = "b";
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write(msg.getBytes());
                        Toast.makeText(getBaseContext(), "Sending Backward...", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Backward failed", Toast.LENGTH_LONG).show();
                    }


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Bluetooth Send End action;
                    String msg = "s";
                    try {
                        outputStream.write(msg.getBytes());
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Stop failed", Toast.LENGTH_LONG).show();
                    }

                }
                // TODO Sends Bluetooth message to move forward end on release
                return false;
            }
        });


        exit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Bluetooth Send Exit action;
                    String msg = "e";
                    try {
                        outputStream = btSocket.getOutputStream();
                        outputStream.write(msg.getBytes());
                        Toast.makeText(getBaseContext(), "Exiting RaspberryPi...", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Exit failed", Toast.LENGTH_LONG).show();
                    }

                }
                return false;
            }
        });

    }
}