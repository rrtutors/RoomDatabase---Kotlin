package com.rrtutors.roomwithkotlin

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class AppPref {
    companion object{
        fun setSession(ctx: Context, _id:Int)
        {
            var sh=ctx.getSharedPreferences("myapp",MODE_PRIVATE);
            var editor=sh.edit()
            editor.putInt("id",_id).commit();
        }
        fun getSessionId(ctx:Context):Int
        {
            return ctx.getSharedPreferences("myapp",MODE_PRIVATE).getInt("id",0)
        }
        fun clearSeesion(ctx:Context)
        {
             ctx.getSharedPreferences("myapp",MODE_PRIVATE).edit().clear().commit()
        }
    }

}