export class AdvUserDto {
    email: string;
    password: string;
    name: string;
    picture: string;
    role: string;

    constructor(e: string, n: string, r:string) {
        this.email = e;
        this.password = "123456"
        this.name = n;
        this.picture = ""
        this.role = r
    }
}