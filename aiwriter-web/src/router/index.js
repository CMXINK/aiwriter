import { createRouter, createWebHistory } from 'vue-router'
import Desk from "../views/desk/Desk"
import Logon from "@/views/logon/Logon";
import Main from "@/views/Main"
import Dashboard from "@/views/dashboard/Dashboard"
import Query from "@/views/dashboard/query/Query"
const routes = [
  {
    path: "/",
    component: Main,
    children: [
      {
        path: "/desk",
        component: Desk
      },
      {
        path: "/logon",
        component: Logon
      }
    ]
  },
  {
    path: "/dashboard",
    component: Dashboard,
    redirect: '/dashboard/title',
    children: [
      {
        name: 'title',
        path: 'title',
        component: Query,
      },
      {
        name: 'introduction',
        path: 'introduction',
        component: Query,
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
