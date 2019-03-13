import { user } from "../model/user";

export interface LoginResponse {
    token: string;
    user: user;
}