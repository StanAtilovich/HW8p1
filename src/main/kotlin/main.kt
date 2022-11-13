import NoteService.add
import NoteService.createComment
import NoteService.delete
import NoteService.deleteComment
import NoteService.get
import NoteService.getById
import NoteService.getComments
import NoteService.restoreComment

fun main() {
    val note1 = Note(1, 111, "note1", "text1", 11222, deleted = true)
    val note2 = Note(2, 222, "note2", "text2", 21222, deleted = true)
    val note3 = Note(3, 333, "note3", "text3", 31222, deleted = true)
    val note4 = Note(4, 444, "note4", "text4", 41222, deleted = true)
    val note5 = Note(5, 555, "note5", "text5", 51222, deleted = true)

    val comment1=Comment(1,1,"комент 1", 1, true)
    val comment2=Comment(2,2,"комент 2", 2, true)
    add(note1)
    add(note2)
    add(note3)
    add(note4)
    add(note5)
    createComment(1, comment1)
    createComment(4, comment2)
    delete(3)
    deleteComment(1)
    get(5)
    getById(1)
    getComments(4)
    restoreComment(1)
}