package com.example.myapplication.feature.librarian.home

import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.core.base.BaseActivity
import com.example.myapplication.databinding.ActivityLibrarianHomeBinding
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.feature.librarian.book.BookFragment
import com.example.myapplication.feature.librarian.category.CategoryFragment
import com.example.myapplication.feature.librarian.loanslip.LoanSlipFragment
import com.example.myapplication.feature.librarian.member.MemberFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibrarianHomeActivity : BaseActivity() {
    private lateinit var binding: ActivityLibrarianHomeBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibrarianHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupNavigation()
        // Màn hình mặc định
        if (savedInstanceState == null) {
            openFragment(BookFragment())
        }

        
    }
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Home"
    }

    private fun setupNavigation() {

        binding.navHost.setNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.item_book -> {
                    openFragment(BookFragment())
                    supportActionBar?.title = "Book"
                    true
                }

                R.id.item_category -> {
                    openFragment(CategoryFragment())
                    supportActionBar?.title = "Category"
                    true
                }
                R.id.item_loan_slip -> {
                    openFragment(LoanSlipFragment())
                    supportActionBar?.title = "Loan Slip"
                    true
                }

                R.id.item_member -> {
                    openFragment(MemberFragment())
                    supportActionBar?.title = "Member"
                    true
                }

                else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.flFragmentContainer,
                fragment
            )
            .commit()

        binding.drawrlayout.closeDrawer(
            GravityCompat.START
        )
    }

}