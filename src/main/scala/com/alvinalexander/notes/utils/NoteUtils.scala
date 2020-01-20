package com.alvinalexander.notes.utils

import com.alvinalexander.notes.Note

object NoteUtils

    def populateShortenedNotes(notes: Seq[Note]): Seq[Note] =
        // TODO want (a) only X number of lines or (b) Y number of characters
        val shortenedNotes = notes.map { n =>
            val shortenedNoteText = n.getNote.take(200)
            Note(
                n.getNote,
                shortenedNoteText,
                n.getUrl,
                n.getTags,
                n.getDateCreated,
                n.getDateUpdated
            )
        }
        shortenedNotes


