import { createRouter, createWebHistory } from 'vue-router'
import Desk from "../views/desk/Desk"
import Logon from "@/views/logon/Logon";
import Main from "@/views/Main"
import Dashboard from "@/views/dashboard/Dashboard"

const routes = [
  {
    path:"/",
    component:Main,
    children:[
        {
      path:"/desk",
      component:Desk
    },
      {
        path : "/logon",
        component: Logon
      }
    ]
  },
  {
    path: "/dashboard",
    component: Dashboard,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
