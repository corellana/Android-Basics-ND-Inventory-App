package com.example.carito.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carito.inventoryapp.data.ProductContract.ProductEntry;
import com.example.carito.inventoryapp.data.ProductCursorAdapter;
import com.example.carito.inventoryapp.data.ProductDbHelper;

/**
 * Displays list of products that were entered and stored in the app.
 */
public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        /// Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductsActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the products database.
     */
    private void displayDatabaseInfo() {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductEntry.COLUMN_PRODUCT_SUPPLIER,
                ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER};

        // Perform a query on the provider using the ContentResolver.
        // Use the {@link PetEntry#CONTENT_URI} to access the pet data.
        Cursor cursor = getContentResolver().query(
                ProductEntry.CONTENT_URI,   // The content URI of the words table
                projection,             // The columns to return for each row
                null,                   // Selection criteria
                null,                   // Selection criteria
                null);                  // The sort order for the returned rows

        // Find the ListView which will be populated with the product data
        ListView productListView = (ListView) findViewById(R.id.list);


        // Setup an Adapter to create a list item for each row of product data in the Cursor.
        ProductCursorAdapter adapter = new ProductCursorAdapter(this, cursor);

        // Attach the adapter to the ListView.
        productListView.setAdapter(adapter);
    }

    /**
     * Helper method to insert hardcoded product data into the database. For debugging purposes only.
     */
    private void insertProduct() {
        // Create a ContentValues object where column names are the keys and Snow Jacket's product
        // attributes are the values.
        ContentValues values = new ContentValues();
        // Make the content values
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, "Snow Jacket");
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, "$199");
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, 23);
        values.put(ProductEntry.COLUMN_PRODUCT_SUPPLIER, 7);
        values.put(ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, "30523234456");

        // Insert a new row for Snow Jacket into the provider using the ContentResolver.
        // Use the {@link ProductEntry#CONTENT_URI} to indicate that we want to insert
        // into the products database table.
        // Receive the new content URI that will allow us to access Snow Jacket's data in the future.
        Uri newUri = getContentResolver().insert(ProductEntry.CONTENT_URI, values);
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_products.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_products, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                // I want to modify the code to call an insert product method.
                insertProduct();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
