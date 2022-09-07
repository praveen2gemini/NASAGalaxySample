package com.cts.galaxy.utils

/**
 * @author Praveen Kumar Sugumaran
 */
interface Recyclable<T> {

    /**
     * This method would recycle the data with respective views
     */
    fun recycle(data: T)
}
