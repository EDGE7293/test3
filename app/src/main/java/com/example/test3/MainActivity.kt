package com.example.test3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageButton
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import android.app.AlertDialog
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        val navigationViewRight: NavigationView = findViewById(R.id.nav_view_right)

        val btnJirisan = findViewById<Button>(R.id.jirisan)
        val btnPalgongsan = findViewById<Button>(R.id.palgongsan)
        val btnRightDrawer = findViewById<AppCompatImageButton>(R.id.right_drawer_button)

        btnJirisan.setOnClickListener {
            navigationView.removeHeaderView(navigationView.getHeaderView(0))
            navigationView.inflateHeaderView(R.layout.navigation_header)
            drawerLayout.openDrawer(navigationView)
        }

        btnPalgongsan.setOnClickListener {
            navigationView.removeHeaderView(navigationView.getHeaderView(0))
            navigationView.inflateHeaderView(R.layout.navigation_header2)
            drawerLayout.openDrawer(navigationView)
        }

        btnRightDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }
        navigationView.setNavigationItemSelectedListener { menuItem ->
            handleMenuItemClick(menuItem)
        }

        navigationViewRight.setNavigationItemSelectedListener { menuItem ->
            handleMenuItemClick(menuItem)
        }
    }

    // 기존 코드에 추가
    private fun handleMenuItemClick(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.nav_exit -> {
                showExitDialog()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun showExitDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("종료")
            .setMessage("종료하시겠습니까?")
            .setPositiveButton("예") { _, _ ->
                finishAffinity() // 액티비티 스택을 모두 종료하고 앱 종료
            }
            .setNegativeButton("아니오") { dialog, _ ->
                dialog.dismiss() // 다이얼로그 창 닫기
            }
            .create()

        alertDialog.show()
    }
}


