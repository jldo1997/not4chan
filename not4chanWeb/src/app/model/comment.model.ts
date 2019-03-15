import { user } from "./user";
import { photo } from "./photo.model";

export class comment {
    id: string;
    user: user;
    photo: photo;
    responseTo: comment;
    content: string;

    constructor(id: string, u: user, p: photo, rt: comment, c: string){
        this.id = id;
        this.user = u;
        this.photo = p;
        this.responseTo = rt;
        this.content = c;
    }

}