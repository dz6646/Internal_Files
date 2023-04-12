package com.example.internal_files;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Input.
     */
    EditText input;
    /**
     * The Show me that dorel.
     */
    TextView showMeThatDorel;
    /**
     * The Filename.
     */
    final String FILENAME = "text.txt";

    /**
     * The Si.
     */
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFile(); // in case there is no file just so the program will work

        String first_text = readFile();

        input = findViewById(R.id.input_name);
        showMeThatDorel = findViewById(R.id.count);
        showMeThatDorel.setText(first_text);
        si = new Intent(this, Credits.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(si);
        return true;
    }

    /**
     * Add text.
     * This function is the onClick function of the button "SAVE" it will save the text into the file
     * @param view the view
     */
    public void addText(View view) {
        try
        {

            String strwr = input.getText().toString();
            FileOutputStream fos = openFileOutput(FILENAME, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            if(!strwr.isEmpty())
                bw.write(" " + strwr);
            bw.close();
            strwr = readFile();
            showMeThatDorel.setText(strwr);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read file string.
     * This function will read the file and return a string with it's contents
     * @return the contents of the file
     */
    public String readFile()
    {
        try
        {
            String line = "";
            String strrd;
            FileInputStream fis= openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            line = br.readLine();
            while (line != null) {
                sb.append(line).append('\n');
                line = br.readLine();
            }
            strrd=sb.toString();
            isr.close();
            return strrd;
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create file.
     * This function will make sure that there is a file on the device
     */
    public void createFile()
    {
        try
        {
            FileOutputStream fos = openFileOutput(FILENAME, MODE_APPEND);
            fos.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reset file.
     * This function will reset all the contents of the file
     * @param view the view
     */
    public void resetFile(View view) {
        try
        {
            FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
            fos.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Exit.
     * This function will update the file and finish the program
     * @param view the view
     */
    public void exit(View view) {
        try {
            String strwr = input.getText().toString();
            FileOutputStream fos = openFileOutput(FILENAME, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(" " + strwr);
            bw.close();
            finish();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}