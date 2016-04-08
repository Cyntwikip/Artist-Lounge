
//var index = 0;
            
function Comment(profilepic, text, commentID) {
    this.profilepic = profilepic;
    this.text = text;
    this.commentID = commentID;
}
            
function addComment(Comment) {
    var profilepic = Comment.profilepic;
    var text = Comment.text
    var index = Comment.commentID;

    var idName = 'comment';
    var comment = $('<div></div>');
    $(comment).addClass("comment");
    $(comment).attr("id", idName + index);
    $("#comments").append(comment);

    var commentID = "#" + idName + index;
    
    var pic = $('<img></img');
    $(pic).addClass("comment-dp");
    $(pic).attr("src", profilepic);
    $(commentID).append(pic);
    
    var commentText = $('<div></div>');
    $(commentText).addClass("comment-text");
    $(commentID).append(commentText);
    
    $(commentText).append(text);
    
}


function emptyCommentSection() {
    $('#comments').empty();
}