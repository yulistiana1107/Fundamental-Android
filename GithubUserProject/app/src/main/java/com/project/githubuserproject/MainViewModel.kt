package com.project.githubuserproject

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class MainViewModel : ViewModel() {
    //Main Acticity dan detail user view model

    companion object {
        private const val GITHUB_API = BuildConfig.GithubAPI
    }

    private val listUsersNonMutable = ArrayList<User>()
    private val listUsersMutable = MutableLiveData<ArrayList<User>>()

    fun getListUsers(): LiveData<ArrayList<User>> {
        return listUsersMutable
    }

    fun getDataGit(context: Context) {
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token $GITHUB_API")
        client.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/users"

        client.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String(it) }
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val usernameLogin = jsonObject.getString("login")
                        getDataGitDetail(usernameLogin, context)
                    }

                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getDataGitSearch(username: String, context: Context) {
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token $GITHUB_API")
        client.addHeader("User-Agent", "request")
        var urlClient = "https://api.github.com/search/users?q=$username"

        client.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String(it) }
                try {
                    listUsersNonMutable.clear()
                    val jsonArray = JSONObject(result)
                    val item = jsonArray.getJSONArray("items")
                    for (i in 0 until item.length()) {
                        val jsonObject = item.getJSONObject(i)
                        val username = jsonObject.getString("login")
                        getDataGitDetail(username, context)
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getDataGitDetail(usernameLogin: String, context: Context) {
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token $GITHUB_API")
        client.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/users/$usernameLogin"

        client.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String(it) }
                try {
                    val jsonObject = JSONObject(result)
                    val usersData = User()
                    usersData.name = jsonObject.getString("login")
                    usersData.detail = jsonObject.getString("name")
                    usersData.photo = jsonObject.getString("avatar_url")
                    usersData.workplace = jsonObject.getString("company")
                    usersData.location = jsonObject.getString("location")
                    usersData.repositories = jsonObject.getString("public_repos")
                    usersData.followers = jsonObject.getString("followers")
                    usersData.following = jsonObject.getString("following")
                    listUsersNonMutable.add(usersData)
                    listUsersMutable.postValue(listUsersNonMutable)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    //Following view model

    fun getDataFollowing(context: Context, id: String) {
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token $GITHUB_API")
        client.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/users/$id/following"

        client.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String(it) }
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val usernameLogin = jsonObject.getString("login")
                        getDataGitDetail(usernameLogin, context)
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    //Followers view model

    fun getDataFollowers(context: Context, id: String) {
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token $GITHUB_API")
        client.addHeader("User-Agent", "request")
        val urlClient = "https://api.github.com/users/$id/followers"

        client.get(urlClient, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String(it) }
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val usernameLogin = jsonObject.getString("login")
                        getDataGitDetail(usernameLogin, context)
                    }

                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}
