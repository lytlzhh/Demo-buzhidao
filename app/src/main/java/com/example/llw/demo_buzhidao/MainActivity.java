package com.example.llw.demo_buzhidao;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnWrite;
    private EditText editWrite;
    private Button btnRead;
    private EditText editRead;
    public String FILE_NAME = "/crazey.bin";

    private void assignViews() {
        btnWrite = (Button) findViewById(R.id.btn_write);
        editWrite = (EditText) findViewById(R.id.edit_write);
        btnRead = (Button) findViewById(R.id.btn_read);
        editRead = (EditText) findViewById(R.id.edit_read);
        btnRead.setOnClickListener(this);
        btnWrite.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_write:
                my_write();
                break;
            case R.id.btn_read:
                my_read();
                break;
        }
    }

    public void my_write() {
        write(editWrite.getText().toString());
    }

    public void my_read() {
        editRead.setText(read());
    }


    public String read() {

        try {
            //首先判断手机是否有SD卡，而且具有读权
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //如果有SD卡则会返回true

                //获取SD卡下的存储目录
                File sdCard = Environment.getExternalStorageDirectory();

                //获取制定文件对应的输入流
                FileInputStream fileInputStream = new FileInputStream(sdCard.getCanonicalPath() + FILE_NAME);

                //将文件输入流包装成bufferread
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

                StringBuilder stringBuilder = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {

                    stringBuilder.append(line);
                }

                bufferedReader.close();
                return stringBuilder.toString();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String contant) {
        try {
            //判断是否有SD卡
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //有SD卡后，进行获取SD卡下面的存储目录
                File sdcad_mulu = Environment.getExternalStorageDirectory();

                //   FileOutputStream fileOutputStream = new FileOutputStream(sdcad_mulu.getCanonicalPath()+)

                File file = new File(sdcad_mulu + FILE_NAME);

                //这个类的功能：随机存，取文件,随机访问文件；相当于文件指针可以指定想写的位置
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

                randomAccessFile.seek(file.length());//将文件指针移动到文件的最后处

                randomAccessFile.write(contant.getBytes());//开始写

                randomAccessFile.close();//关闭文件randomaccessfile

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
