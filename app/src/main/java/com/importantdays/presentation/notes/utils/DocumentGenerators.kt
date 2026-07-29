package com.importantdays.presentation.notes.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.text.StaticLayout
import android.text.TextPaint
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object DocumentGenerators {

    fun generateImageFromNote(context: Context, title: String, content: String): Uri? {
        val width = 1080
        val padding = 60
        val textPaint = TextPaint().apply {
            color = Color.BLACK
            textSize = 40f
            isAntiAlias = true
        }
        val titlePaint = TextPaint().apply {
            color = Color.BLACK
            textSize = 60f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        }

        val titleLayout = StaticLayout.Builder.obtain(title, 0, title.length, titlePaint, width - padding * 2).build()
        val contentLayout = StaticLayout.Builder.obtain(content, 0, content.length, textPaint, width - padding * 2).build()

        val height = padding * 3 + titleLayout.height + contentLayout.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)

        canvas.save()
        canvas.translate(padding.toFloat(), padding.toFloat())
        titleLayout.draw(canvas)
        canvas.translate(0f, (titleLayout.height + padding).toFloat())
        contentLayout.draw(canvas)
        canvas.restore()

        return saveBitmapToCache(context, bitmap, "note_${System.currentTimeMillis()}.png")
    }

    fun generatePdfFromNote(context: Context, title: String, content: String): Uri? {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val padding = 40
        val textPaint = TextPaint().apply {
            color = Color.BLACK
            textSize = 12f
            isAntiAlias = true
        }
        val titlePaint = TextPaint().apply {
            color = Color.BLACK
            textSize = 18f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        }

        val titleLayout = StaticLayout.Builder.obtain(title, 0, title.length, titlePaint, pageInfo.pageWidth - padding * 2).build()
        val contentLayout = StaticLayout.Builder.obtain(content, 0, content.length, textPaint, pageInfo.pageWidth - padding * 2).build()

        canvas.save()
        canvas.translate(padding.toFloat(), padding.toFloat())
        titleLayout.draw(canvas)
        canvas.translate(0f, (titleLayout.height + padding).toFloat())
        contentLayout.draw(canvas)
        canvas.restore()

        pdfDocument.finishPage(page)

        return savePdfToCache(context, pdfDocument, "note_${System.currentTimeMillis()}.pdf")
    }

    private fun saveBitmapToCache(context: Context, bitmap: Bitmap, fileName: String): Uri? {
        return try {
            val cachePath = File(context.cacheDir, "images")
            cachePath.mkdirs()
            val file = File(cachePath, fileName)
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()
            FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun savePdfToCache(context: Context, document: PdfDocument, fileName: String): Uri? {
        return try {
            val cachePath = File(context.cacheDir, "docs")
            cachePath.mkdirs()
            val file = File(cachePath, fileName)
            val stream = FileOutputStream(file)
            document.writeTo(stream)
            document.close()
            stream.close()
            FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
