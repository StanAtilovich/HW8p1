data class Note(
    var id: Int,
    var ownerId: Int,
    val title: String,
    val text: String,
    val data: Int,
    var deleted: Boolean = false
) {
    override fun toString(): String {
        return "Note:(id=$id, ownerId=$ownerId,title=$title,text=$text,data=$data,delered=$deleted)"
    }
}

data class Comment(
    var idNote: Int,
    var ownerId: Int,
    val message: String,
    var idComment: Int,
    var deleted: Boolean = true
){override fun toString(): String {
    return "Comment:(idNote=$idNote, ownerId=$ownerId,idComment=$idComment,deleted=$deleted,message=$message)"
}

}
