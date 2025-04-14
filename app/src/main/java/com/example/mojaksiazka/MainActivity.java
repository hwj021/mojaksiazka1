package com.example.mojaksiazka;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button b1;
    private Button b3;
    private Button b4;
    private TextView t1;
    private int zmienna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b1 = findViewById(R.id.opis);
        b3 = findViewById(R.id.button3);
        t1 = findViewById(R.id.textView1);
        b4 = findViewById(R.id.button4);
        zmienna = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel kanal = new NotificationChannel(
                    "powiadomienie",
                    "Kanał Powiadomień",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(kanal);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(
                            MainActivity.this,
                            "powiadomienie"
                    );
                    builder.setContentTitle("Moja Książka");
                    builder.setContentText("Krótki opis: Ekscytująca historia pełna zwrotów akcji.");
                    builder.setSmallIcon(R.drawable.ic_launcher_foreground);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                    managerCompat.notify(1, builder.build());
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zmienna == 0) {
                    b3.setText("USUŃ Z CHCĘ PRZECZYTAĆ");
                    t1.setVisibility(View.VISIBLE);
                    zmienna++;
                } else if (zmienna == 1) {
                    b3.setText("DODAJ DO CHCE PRZECZYTAC");
                    t1.setVisibility(View.GONE);
                    zmienna--;
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(
                            MainActivity.this,
                            "powiadomienie"
                    );
                    builder.setContentTitle("Moja Książka");
                    builder.setContentText("Pamiętaj, aby znaleźć czas na lekturę!");
                    builder.setSmallIcon(R.drawable.ic_launcher_foreground);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                    managerCompat.notify(2, builder.build());
                }
            }
        });
    }
}
