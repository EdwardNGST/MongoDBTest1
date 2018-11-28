package com.example.alan_.mongodbtest1;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MainActivity extends AppCompatActivity {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private MongoClientURI connectionString;
    private EditText txtIP;
    private EditText txtName;
    private EditText txtLastName;
    private EditText txtCareer;
    private EditText txtStudentAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtIP = findViewById(R.id.txtIP);
        txtName = findViewById(R.id.txtName);
        txtLastName = findViewById(R.id.txtLastName);
        txtCareer = findViewById(R.id.txtCareer);
        txtStudentAge = findViewById(R.id.txtStudentAge);
        Button btnSetIP = findViewById(R.id.btnSetIP);

        /*if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }*/

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnSetIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = txtIP.getText().toString();
                connectionString = new MongoClientURI("mongodb://"+ip+":27017");
                mongoClient = new MongoClient(connectionString);

                database = mongoClient.getDatabase("bdPrueba1");
                collection = database.getCollection("alumnos");
            }
        });
    }



    public void registerNewDoc(View view) {
        String name=txtName.getText().toString();
        String lastName=txtLastName.getText().toString();
        String career=txtCareer.getText().toString();
        int age= Integer.parseInt(txtStudentAge.getText().toString());
        Document doc = new Document("nombre", name).append("apellidos", lastName).append("carrera", career).append("age", age);

        collection.insertOne(doc);

        //Conectarse con mongo -u root -p 1234 192.168.1.68/admin
    }
}
