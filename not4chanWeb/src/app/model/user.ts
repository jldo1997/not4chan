export class user {
    email: string;
    id: string;
    name: string;
    picture: string;
    role: string;

    constructor(e: string, id: string, n: string, pic: string, r: string) {
        this.email = e;
        this.id = id;
        this.name = n;
        this.picture = pic;
        this.role = r;
    }
}