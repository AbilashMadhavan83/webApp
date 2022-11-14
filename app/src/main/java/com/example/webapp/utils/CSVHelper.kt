package com.example.gofly.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*


object CSVHelper {

    fun write(context: Context, data: String?, fileName: String?) {

        //val data: String = "Test 12345 context"
        //val fileName = "SampleData.CSV"

        try {

            val fileOutputStream: FileOutputStream = context.openFileOutput(fileName,
                AppCompatActivity.MODE_PRIVATE
            )
            if (data != null) {
                fileOutputStream.write(data.toByteArray())
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun read(context: Context, fileName: String):ArrayList<String>? {

        val listData: ArrayList<String>?  = ArrayList()

        //val fileName = "SampleData.CSV"
        if (fileName.trim() != "") {
            var fileInputStream: FileInputStream? = null
            fileInputStream = context.openFileInput(fileName)
            val inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)

            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null


            while (run {
                    text = bufferedReader.readLine()
                    text
                } != null) {
                listData?.add(text.toString())
                //stringBuilder.append(text)
            }
            //Toast.makeText(context, stringBuilder.toString(), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "name of the file can't be blank", Toast.LENGTH_SHORT).show()
        }

        return listData
    }



}