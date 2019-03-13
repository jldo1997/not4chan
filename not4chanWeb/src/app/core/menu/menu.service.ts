import { Injectable } from '@angular/core';

export interface BadgeItem {
  type: string;
  value: string;
}

export interface ChildrenItems {
  state: string;
  name: string;
  type?: string;
}

export interface Menu {
  state: string;
  name: string;
  type: string;
  icon: string;
  badge?: BadgeItem[];
  children?: ChildrenItems[];
}

const MENUITEMS = [
  {
    state: '/',
    name: 'HOME',
    type: 'link',
    icon: 'explore'
  },
  {
    state: 'dashboard',
    name: 'List',
    type: 'sub',
    icon: 'local_library',
    children: [
      {state: 'user-table', name: 'Users list',type: 'link'},
      {state: 'comment-table', name: 'Comments list',type: 'link'},
      {state: 'thread-table', name: 'Threads list',type: 'link'},
    ]
  }
];

@Injectable()
export class MenuService {
  getAll(): Menu[] {
    return MENUITEMS;
  }

  add(menu) {
    MENUITEMS.push(menu);
  }
}
