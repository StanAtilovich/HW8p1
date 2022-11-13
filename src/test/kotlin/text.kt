import NoteService.add
import NoteService.createComment
import NoteService.delete
import NoteService.deleteComment
import NoteService.edit
import NoteService.editComment
import NoteService.restoreComment
import org.junit.Assert.assertEquals
import org.junit.Test

class NoteServiceTest {
    @Test
    fun addNote() {
        val note = Note(1, 111, "note1", "text1", 11222, deleted = true)
        val serviceNotes = NoteService
        val result = serviceNotes.add(note).id != 0
        assertEquals(true, result)
    }

    @Test
    fun deleteNoteTrue() {
        val note1 = Note(1, 111, "note1", "text1", 11222, deleted = true)
        val note2 = Note(2, 222, "note1", "text2", 12222, deleted = true)
        val note3 = Note(3, 333, "note1", "text3", 13222, deleted = true)
        val note4 = Note(4, 444, "note1", "text4", 14222, deleted = true)

        val serviceNotes = NoteService
        serviceNotes.add(note1)
        serviceNotes.add(note2)
        serviceNotes.add(note3)
        serviceNotes.add(note4)
        val result = serviceNotes.delete(3)
        assertEquals(true, result)

    }

    @Test
    fun deleteNoteFalse() {
        val note1 = Note(1, 111, "note1", "text1", 11222, deleted = true)
        val note2 = Note(2, 222, "note1", "text2", 12222, deleted = true)
        val note3 = Note(3, 333, "note1", "text3", 13222, deleted = true)
        val note4 = Note(4, 444, "note1", "text4", 14222, deleted = true)

        val serviceNotes = NoteService
        serviceNotes.add(note1)
        serviceNotes.add(note2)
        serviceNotes.add(note3)
        serviceNotes.add(note4)
        val result = serviceNotes.delete(2)
        assertEquals(true, result)

    }

    @Test
    fun testCreateCommentTrue() {
        val noteTest: Note = Note(1, 111, "note1", "text1", 11222, deleted = true)
        noteTest.id = 1
        val commentTest: Comment = Comment(1, 1, "comment 1", 1, true)
        commentTest.idComment = 1
        assertEquals(commentTest.idComment, noteTest.id)
    }
    @Test
    fun testCreateCommentFalse() {
        val noteTest: Note = Note(1, 111, "note1", "text1", 11222, deleted = true)
        noteTest.id = 1
        val commentTest: Comment = Comment(1, 1, "comment 1", 1, false)
        commentTest.idComment = 1
        assertEquals(commentTest.idComment, noteTest.id)
    }
    @Test
    fun testDeleteComment() {
        val noteTest: Note = Note(1, 111, "note1", "text1", 11222, deleted = true)
        add(noteTest)
        val commentTest: Comment = Comment(1, 1, "comment 1", 1, true)
        createComment(1, commentTest)
        deleteComment(1)
        commentTest.deleted = true
        val result: Boolean = true
        assertEquals(commentTest.deleted, result)
    }
    @Test
    fun testEdit() {
        val noteTest: Note = Note(1, 111, "note1", "text1", 11222, deleted = true)
        add(noteTest)
        noteTest.id = 1
        edit(1, noteTest)
        noteTest.deleted = false
        val result: Boolean = false
        assertEquals(noteTest.deleted, result)
    }
    @Test
    fun testEditComment() {
        val noteTest: Note = Note(1, 111, "note1", "text1", 11222, deleted = true)
        add(noteTest)
        val commentTest: Comment = Comment(1, 1, "comment 1", 1, true)
        createComment(1, commentTest)
        editComment(1, commentTest)
        commentTest.deleted = false
        val result: Boolean = false
        assertEquals(commentTest.deleted, result)
    }
    @Test
    fun testGetNote() {
        val noteTest: Note = Note(1, 111, "note1", "text1", 11222, deleted = true)
        add(noteTest)
        val expected = "note1"
        assertEquals(noteTest.title, expected)
    }
    @Test
    fun testGetById() {
        val note: Note = Note(1, 111, "note1", "text1", 11222, deleted = true)
        val expected = println("""title: title, text: text""")
        val result = println("""title: ${note.title}, text: ${note.text}""")
        assertEquals(expected, result)
    }
    @Test
    fun testGetComments() {
        val note: Note = Note(1, 111, "note1", "text1", 11222, deleted = true)
        val comment: Comment = Comment(1, 1, "comment 1", 1, true)
        add(note)
        createComment(1, comment)
        val expected = comment.message
        val result = "comment 1"
        assertEquals(expected, result)
    }

    @Test(expected = NoteException::class)
    fun testExceptionDeleteNote() {
        val expected = NoteException::class
        delete(2)
        val result = NoteException::class
        assertEquals(expected, result)
    }

    @Test(expected = CommentException::class)
    fun testExceptionDeleteComment() {
        val expected = CommentException::class
        deleteComment(5)
        val result = CommentException::class
        assertEquals(expected, result)
    }
    @Test(expected = CommentException::class)
    fun testExceptionEditComment() {
        val commentTest: Comment = Comment(0, 0," ", 0, true)
        val expected = CommentException::class
        editComment(5, commentTest)
        val result = CommentException::class
        assertEquals(expected, result)
    }
    @Test(expected = CommentRestoreException::class)
    fun testExceptionRestoreComment() {
        val commentTest: Comment = Comment(1, 0," ", 1, true)
        commentTest.idComment = 1
        commentTest.deleted = true
        val expected = CommentRestoreException::class
        restoreComment(5)
        val result = CommentRestoreException::class
        assertEquals(expected, result)
    }

}

