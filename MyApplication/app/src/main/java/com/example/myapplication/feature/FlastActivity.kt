package com.example.myapplication.feature

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.coroutineScope
import com.example.myapplication.R
import com.example.myapplication.core.base.BaseActivity
import com.example.myapplication.core.datastore.DatabaseVersionManager
import com.example.myapplication.core.datastore.SessionManager
import com.example.myapplication.core.datastore.TokenManager
import com.example.myapplication.core.util.Constants
import com.example.myapplication.feature.admin.home.AdminHomeActivity
import com.example.myapplication.feature.auth.LoginActivity
import com.example.myapplication.feature.initdata.InitDataActivity
import com.example.myapplication.feature.librarian.home.LibrarianHomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FlastActivity : BaseActivity() {

    @Inject
    lateinit var sessionManager: SessionManager;

    @Inject
    lateinit var databaseVersionManager: DatabaseVersionManager;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flast)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        checkSesssion();
    }

    private fun checkSesssion() {
        lifecycle.coroutineScope.launch {
            delay(500);
            val session = sessionManager.getSession()
            if (session != null) {
                val role = session.role;
                val databaseInitialized = (databaseVersionManager.getUpdateTime() != null)
                if (databaseInitialized == true) {
                    if (role == Constants.ROLE_LIBRARIAN) startActivity(
                        Intent(
                            this@FlastActivity, LibrarianHomeActivity::class.java
                        )

                    ) else {
                        startActivity(
                            Intent(
                                this@FlastActivity, AdminHomeActivity::class.java
                            )
                        )
                    }
                } else {
                    val i = Intent(
                        this@FlastActivity, InitDataActivity::class.java
                    )
                    i.putExtra("role", role)
                    startActivity(i)
                }
            } else {
                startActivity(
                    Intent(
                        this@FlastActivity, LoginActivity::class.java
                    )
                )
            }
            finish()
        }
    }
}
//
//    App khởi động
//    ↓
//    SessionManager
//    ↓
//    Có session/token local?
//    ├── Không → Login
//    │
//    └── Có
//    ↓
//    SyncManager
//    ↓
//    gọi API sync
//    ↓
//    Server Security
//    ↓
//    kiểm tra session/securityVersion
//    │
//    ┌──┴───────────┐
//    │              │
//    Không hợp lệ    Hợp lệ
//    │              │
//    ↓              ↓
//    Xóa session       kiểm tra JWT
//    + local data          │
//    │           ┌──┴──┐
//    ↓           │     │
//    Login       valid  invalid
//    │     │
//    ↓     ↓
//    sync   Login
//}