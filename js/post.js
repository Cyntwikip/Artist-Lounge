
var index = 0;
            
function Post(name, image, profilepic) {
    this.name = name;
    this.image = image;
    this.profilepic = profilepic;
}
            
function addPost(Post) {
    var pname = Post.name;
    var image = Post.image;
    var profilepic = Post.profilepic;

    var idName = 'post';
    var post = $('<div></div>');
    $(post).addClass("post");
    $(post).attr("id", idName + index);
    $("#posts").append(post);

    var postID = "#" + idName + index;

    var paint = $('<div></div>');
    $(paint).addClass("post-paint");
    $(paint).css("background-image", 'url(' + image + ')');
    $(postID).append(paint);

//                var paint = $('<img></img');
//                $(paint).addClass("post-paint");
//                $(paint).attr("src", image);
//                $(postID).append(paint);

    var details = $('<div></div>');
    $(details).addClass("post-details");
    $(postID).append(details);

    var pic = $('<img></img');
    $(pic).addClass("post-dp");
    $(pic).attr("src", profilepic);
    $(details).append(pic);

    var name = $('<div></div');
    $(name).addClass("post-name");
    $(details).append(name);

    $(name).append(pname);

    index++;
}


