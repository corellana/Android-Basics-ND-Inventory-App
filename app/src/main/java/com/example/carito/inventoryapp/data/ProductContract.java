package com.example.carito.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * Contract for the Inventory app.
 */
public class ProductContract {
    private ProductContract(){}

    /**
     * Inner class that defines constant values for the products database table.
     * Each entry in the table represents a single product.
     */
    public static final class ProductEntry implements BaseColumns{
        /** Name of database table for products */
        public static final String TABLE_NAME = "products";

        /**
         * Unique ID number for the product (only for use in the database table).
         *
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Name of the product.
         *
         * Type: TEXT
         */
        public static final String COLUMN_PRODUCT_NAME= "Name";

        /**
         * Price of the product.
         *
         * Type: TEXT
         */
        public static final String COLUMN_PRODUCT_PRICE = "Price";

        /**
         * Quantity of the product.
         *
         * Type: INT
         */
        public static final String COLUMN_PRODUCT_QUANTITY = "Quantity";

        /**
         * Supplier of the product.
         *
         * Type: TEXT
         */
        public static final String COLUMN_PRODUCT_SUPPLIER = "Supplier";

        /**
         * Supplier Phone Number of the product.
         *
         * Type: TEXT
         */
        public static final String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "SupplierPhoneNumber";
    }
}
