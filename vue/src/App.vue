<script lang="ts" setup>
import { RouterView } from "vue-router";
import { useAuthStore } from "@/stores/authStore";

const auth = useAuthStore();
auth.fetchTokens();
</script>

<template>
  <RouterView />
</template>
