interface NoteServiceInterface <N,C>{
    fun add (note: N):N
    fun createComment(id:Int, comment: C):C
    fun deleted(id:Int):Boolean
    fun deletedComment(id: Int):Boolean
    fun edit(id: Int):Boolean
    fun editComment(id: Int,comment: C):Boolean
    fun get(id: Int)
    fun getById(id: Int)
    fun getComments(id: Int)
    fun restoreComment(id: Int):Boolean
    fun deleteComment(idComment: Int): Boolean
    fun edit(id: Int, newNote: Note): Boolean
    fun delete(id: Int): Boolean
}
object NoteService: NoteServiceInterface<Note, Comment> {
    var notes = mutableListOf<Note>()
    var comments = mutableListOf<Comment>()

    override fun add(note: Note): Note {
        val newNote = note.copy(id = notes.size + 1, deleted = false)
        notes.plusAssign(newNote)
        return notes.last()
    }

    override fun createComment(id: Int, comment: Comment): Comment {
        for (note in notes) {
            if (!note.deleted) {
                if (id == note.id) {
                    val newComment = comment.copy(idNote= note.id, idComment =  comments.size + 1,
                        message = comment.message, deleted = false)
                    comments.plusAssign(newComment)
                    return comments.last()
                }
            }
        }
        throw NoteException("Notes with this ID do not exist")
    }


    override fun delete(id: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (!note.deleted) {
                if (id == note.id) {
                    notes[index] = note.copy(id = note.id, deleted = true)
                    return true
                }
            }
        }
        throw NoteException("Notes with this ID do not exist")
    }

    override fun deleteComment(idComment: Int): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (!comment.deleted) {
                if (idComment == comment.idComment) {
                    comments[index] = comment.copy(idComment = comment.idComment, deleted = true)
                    return true
                }
            }
        }
        throw CommentException("Comments with this ID do not exist")
    }

    override fun edit(id: Int, newNote: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (!note.deleted) {
                if (id == note.id) {
                    notes[index] = note.copy(id = note.id, ownerId = note.id, title = newNote.title,
                        text = newNote.text, deleted = false)
                    return true
                }
            }
        }
        throw NoteException("Notes with this ID do not exist")
    }

    override fun deleted(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun deletedComment(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun edit(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun editComment(commentId: Int, newComment: Comment): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (!comment.deleted) {
                if (commentId == comment.idComment) {
                    comments[index] = comment.copy(idNote  = comment.idNote, idComment = comment.idComment,
                        deleted = false, message = newComment.message)
                    return true
                }
            }
        }
        throw CommentException("Comments with this ID do not exist")
    }

    override fun get(ownerId: Int) {
        for (note in notes) {
            if (ownerId == note.id) {
                if (!note.deleted) {
                    println(note.title)
                }
            }
        }
    }

    override fun getById(id: Int) {
        for (note in notes) {
            if (id == note.id) {
                if (!note.deleted) {
                    println("""title: ${note.title}, text: ${note.text}""")
                }
            }
        }
    }

    override fun getComments(id: Int) {
        for (comment in comments) {
            if (id == comment.idNote) {
                println(comment.message)
            }
        }
    }

    override fun restoreComment(commentId: Int): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (commentId == comment.idComment) {
                if (comment.deleted) {
                    comments[index] = comment.copy(idComment = comment.idComment, deleted = false)
                    return true
                }
            }
        }
        throw CommentRestoreException("There is no deleted comment with this ID")
    }
}

class NoteException(message: String) : RuntimeException("Notes with this ID do not exist")
class CommentException(message: String) : RuntimeException("Comments with this ID do not exist")
class CommentRestoreException(message: String) : RuntimeException("There is no deleted comment with this ID")
