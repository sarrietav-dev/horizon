import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import { useAuthStore } from "@/stores/authStore";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../views/LoginView.vue"),
      beforeEnter: (to, from) => {
        const auth = useAuthStore();
        if (auth.isAuthenticated) return { path: from.path };
      },
    },
  ],
});

router.beforeEach((to) => {
  const auth = useAuthStore();
  if (!auth.isAuthenticated && to.name !== "login") return { name: "login" };
});

export default router;
