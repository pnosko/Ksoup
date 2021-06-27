package com.kevin.ksoup.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.ksoup.sample.net.HtmlConverterFactory
import com.kevin.ksoup.sample.net.StringConvertFactory
import com.kevin.net.HttpHelper
import com.kevin.ksoup.sample.net.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化HttpHelper
        HttpHelper.init("https://home.meishichina.com", isReleased = false) {
            it.addConverterFactory(HtmlConverterFactory.create())
            it.addConverterFactory(StringConvertFactory.create())
        }

        // 获取菜谱首页数据
        GlobalScope.launch(Dispatchers.Main) {
            val recipeHome = apiService.recipeHome()
            recipeHome.recipeList?.forEach {
                println("title = ${it.title}, url = ${it.url}, totalCount = ${it.totalCount}")
            }

            val result = apiService.requestUrl("https://fanyi.baidu.com/#en/zh/Relative%20Url")
            println("result = $result")
        }
    }

    companion object {
    }
}