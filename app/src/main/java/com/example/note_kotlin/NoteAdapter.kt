package com.example.note_kotlin

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class NoteAdapter(var listener: INoteListener?) :
    BaseQuickAdapter<Notes, BaseViewHolder>(R.layout.item_note) {
    override fun convert(baseViewHolder: BaseViewHolder, notes: Notes) {
        baseViewHolder.setText(R.id.tv_title, notes.title)
        baseViewHolder.getView<View>(R.id.close).setOnClickListener {
            if (listener != null) {
                listener!!.onNoteClick(notes)
            }
        }
    }
}