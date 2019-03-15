export class Ban {
    role: string;

    constructor(f : boolean) {
        if(f) {
            this.role = "banned";
        } else {
            this.role = "user";
        }
        
    }
}