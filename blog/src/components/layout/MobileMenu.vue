<template>
  <transition
    enter-active-class="transition-opacity duration-200"
    enter-from-class="opacity-0"
    enter-to-class="opacity-100"
    leave-active-class="transition-opacity duration-200"
    leave-from-class="opacity-100"
    leave-to-class="opacity-0"
  >
    <div
      v-if="isOpen"
      class="fixed inset-0 z-50 md:hidden bg-black bg-opacity-50"
      @click="$emit('close')"
    >
      <div
        class="absolute top-16 right-4 bg-white dark:bg-gray-800 rounded-lg shadow-lg py-2 min-w-[120px] border border-gray-200 dark:border-gray-700"
      >
        <nav class="space-y-1">
          <router-link
            to="/"
            class="block px-4 py-2 text-sm text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
            active-class="text-primary-600 dark:text-primary-400 bg-primary-50 dark:bg-primary-900/20"
            @click="$emit('close')"
          >
            首页
          </router-link>
          <router-link
            to="/blog"
            class="block px-4 py-2 text-sm text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
            active-class="text-primary-600 dark:text-primary-400 bg-primary-50 dark:bg-primary-900/20"
            @click="$emit('close')"
          >
            博客
          </router-link>
          <router-link
            to="/about"
            class="block px-4 py-2 text-sm text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
            active-class="text-primary-600 dark:text-primary-400 bg-primary-50 dark:bg-primary-900/20"
            @click="$emit('close')"
          >
            关于
          </router-link>
        </nav>
      </div>
    </div>
  </transition>
</template>

<script setup>
defineProps({
  isOpen: {
    type: Boolean,
    required: true,
  },
});

defineEmits(['close']);
</script>
