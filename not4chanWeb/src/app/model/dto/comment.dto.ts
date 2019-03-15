export class CommentDto {
    user: string;
    content: string;
    

    constructor(u: string, c: string) {
        this.user = u;
        this.content = c;
    }
}

export class ExtendCommentDtoP extends CommentDto{
    photo: string;
    

    constructor(u: string, p: string, c: string) {
        super(u, c);
        this.photo = p;
    }
}

export class ExtendCommentDtoPR extends CommentDto{
    photo: string;
    responseTo: string;
    

    constructor(u: string, p: string, rt:string, c: string) {
        super(u, c);
        this.photo = p;
        this.responseTo = rt;
    }
}

export class ExtendCommentDtoR extends CommentDto{
    responseTo: string;
    

    constructor(u: string, rt:string, c: string) {
        super(u, c);
        this.responseTo = rt;
    }
}


