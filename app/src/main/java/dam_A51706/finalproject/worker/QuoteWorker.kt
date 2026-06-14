package dam_A51706.finalproject.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dam_A51706.finalproject.MainActivity
import dam_A51706.finalproject.R
import dam_A51706.finalproject.data.api.RetrofitClient

class QuoteWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val response = RetrofitClient.api.getRandomQuote()
            if (response.isNotEmpty()) {
                val quote = response[0]
                showNotification(quote.q, quote.a)
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            Log.e("QuoteWorker", "Error fetching quote", e)
            Result.retry()
        }
    }

    private fun showNotification(quote: String, author: String) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "daily_quote_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Daily Motivational Quotes",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel for daily motivational quotes"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.icone_estrela)
            .setContentTitle("Daily Motivation: $author")
            .setContentText(quote)
            .setStyle(NotificationCompat.BigTextStyle().bigText(quote))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(1001, builder.build())
    }
}