export class photo {
    id: string;
    deleteHash: string;
    url: string;

    constructor(id: string, dh: string, url: string ) {
        this.id = id;
        this.deleteHash = dh;
        this.url = url;
    }
}

