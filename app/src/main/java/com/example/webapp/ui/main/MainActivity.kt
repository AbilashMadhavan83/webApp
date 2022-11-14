package com.example.webapp.ui.main
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.webapp.R
import com.example.webapp.app.App
import com.example.webapp.databinding.ActivityMainBinding
import com.example.webapp.ui.retrofit.retrieveData.RetrieveDataFragmentDirections
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModel
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var navHostFragment:NavHostFragment

    private val viewModel: SharedViewModel by viewModels() {
        SharedViewModelFactory((application as App).repository)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the navigation host fragment from this Activity
        navHostFragment = supportFragmentManager
            .findFragmentById(binding.navHost.id) as NavHostFragment

        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController

        // Make sure actions in the ActionBar get propagated to the NavController
        setupActionBarWithNavController(navController)
    }

    /**
     * Enables back button support. Simply navigates one element up on the stack.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    /**
     * 1. Retrieve data
     * 2. Path parameters   - If you want to identify a particular resource
     * 3. Query parameters  - If you want to sort or filter items.
     * 4. Query map         - When you have a lot of Query Params then use Query Map.
     * 5. Path  parameters with Query parameters
     */

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_delete -> Toast.makeText(this,"Delete selected", Toast.LENGTH_SHORT).show()
            R.id.nav_favorite -> Toast.makeText(this,"Favorite selected", Toast.LENGTH_SHORT).show()
            R.id.menu_PathParameters -> goToPathParameters() //Retrieve user who has id 47
            R.id.menu_Query_parameters -> goToQueryparameters()
            R.id.menu_Query_Map -> goToQueryMap()
            R.id.menu_Load_image_using_Glide -> LoadPhotos()
            R.id.menu_Load_crud_operation  -> Load_crud_operation()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun Load_crud_operation() {
        val action = RetrieveDataFragmentDirections.actionRetrieveDataFragmentToCRUDOperationFragment()
        navHostFragment.findNavController().navigate(action)
    }

    private fun LoadPhotos() {
        val action = RetrieveDataFragmentDirections.actionRetrieveDataFragmentToLoadImageUsingGlideFragment()
        navHostFragment.findNavController().navigate(action)
    }

    private fun goToQueryMap() {
        val action = RetrieveDataFragmentDirections.actionRetrieveDataFragmentToQueryMapFragment()
        navHostFragment.findNavController().navigate(action)
    }

    private fun goToQueryparameters() {
        val action = RetrieveDataFragmentDirections.actionRetrieveDataFragmentToQueryParametersFragment()
        navHostFragment.findNavController().navigate(action)
    }

    private fun goToPathParameters() {
//        val fragmentInstance = supportFragmentManager.findFragmentById(R.id.frameLayout)
//
//        if(fragmentInstance is FirstFragment){
//            Toast.makeText(this, "FirstFragment found", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
//        }
        val action = RetrieveDataFragmentDirections.actionRetrieveDataFragmentToPathParametersFragment()
        navHostFragment.findNavController().navigate(action)
    }

}

