<template>
  <div class="min-h-screen bg-gray-50 dark:bg-gray-900 py-8">
    <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Back Button -->
      <el-button
        type="primary"
        text
        class="mb-6"
        @click="$router.back()"
      >
        <el-icon><ArrowLeft /></el-icon>
        返回
      </el-button>

      <!-- Article Content -->
      <div
        v-if="article"
        class="bg-white dark:bg-gray-800 rounded-lg p-8 shadow-sm border border-gray-200 dark:border-gray-700"
      >
        <!-- Article Header -->
        <div class="mb-8">
          <div class="flex items-center justify-between mb-4">
            <span
              class="px-3 py-1 bg-primary-100 dark:bg-primary-900 text-primary-700 dark:text-primary-300 text-sm font-medium rounded-full"
            >
              {{ article.category }}
            </span>
            <div class="flex items-center space-x-4 text-sm text-gray-500 dark:text-gray-400">
              <span>{{ article.readTime }} 分钟阅读</span>
              <span>{{ article.views }} 次阅读</span>
              <span>{{ formatDate(article.createdAt) }}</span>
            </div>
          </div>

          <h1 class="text-3xl md:text-4xl font-bold text-gray-900 dark:text-white mb-4">
            {{ article.title }}
          </h1>

          <p class="text-lg text-gray-600 dark:text-gray-300 mb-6">
            {{ article.excerpt }}
          </p>

          <div class="flex flex-wrap gap-2">
            <span
              v-for="tag in article.tags"
              :key="tag"
              class="px-3 py-1 bg-gray-100 dark:bg-gray-700 text-gray-700 dark:text-gray-300 text-sm rounded-full"
            >
              {{ tag }}
            </span>
          </div>
        </div>

        <!-- Article Body -->
        <div class="prose prose-lg max-w-none dark:prose-invert prose-gray">
          <!-- This would be your actual article content -->
          <div
            class="border-l-4 border-primary-500 bg-primary-50 dark:bg-primary-900/20 pl-6 py-4 mb-8"
          >
            <p class="text-primary-700 dark:text-primary-300 font-medium">
              这是一篇示例文章。在实际项目中，这里会显示从Markdown或其他格式解析的真实文章内容。
            </p>
          </div>

          <h2>文章内容示例</h2>
          <p>这里应该是文章的实际内容。在实际部署中，您可以通过以下方式加载内容：</p>

          <h3>1. Markdown文件</h3>
          <p>将文章保存为Markdown文件，使用markdown-it或其他库进行解析：</p>
          <pre><code class="language-javascript">import markdownIt from 'markdown-it'
const md = new markdownIt()
const htmlContent = md.render(markdownText)</code></pre>

          <h3>2. 数据库存储</h3>
          <p>或者将文章内容存储在数据库中：</p>
          <pre><code class="language-javascript">// 从API获取文章内容
const response = await fetch(`/api/articles/${articleId}`)
const articleData = await response.json()</code></pre>

          <h3>3. 静态生成</h3>
          <p>对于博客网站，静态生成(SSG)是很好的选择：</p>
          <pre><code class="language-javascript">// 在构建时生成静态页面
export async function getStaticProps() {
  const articles = await getArticles()
  return { props: { articles } }
}</code></pre>

          <blockquote>
            <p>
              提示：根据您的需求选择合适的文章存储和渲染方式。对于个人博客，静态生成通常是最佳选择。
            </p>
          </blockquote>
        </div>

        <!-- Article Footer -->
        <div class="mt-12 pt-8 border-t border-gray-200 dark:border-gray-700">
          <div class="flex flex-col sm:flex-row justify-between items-center gap-4">
            <div class="flex items-center space-x-4">
              <span class="text-sm text-gray-500 dark:text-gray-400">分享这篇文章：</span>
              <div class="flex space-x-2">
                <el-button
                  type="primary"
                  text
                  size="small"
                >
                  <el-icon><Share /></el-icon>
                </el-button>
                <el-button
                  type="success"
                  text
                  size="small"
                >
                  <el-icon><Link /></el-icon>
                </el-button>
              </div>
            </div>

            <div class="flex items-center space-x-4">
              <el-button
                type="primary"
                text
                size="small"
                @click="handleLike"
              >
                <el-icon><Star /></el-icon>
                点赞
              </el-button>
              <el-button
                type="primary"
                text
                size="small"
                @click="handleBookmark"
              >
                <el-icon><Bookmark /></el-icon>
                收藏
              </el-button>
            </div>
          </div>
        </div>
      </div>

      <!-- Related Articles -->
      <div class="mt-12">
        <h2 class="text-2xl font-bold text-gray-900 dark:text-white mb-6">
          相关文章
        </h2>
        <div class="grid md:grid-cols-2 gap-6">
          <div
            v-for="relatedArticle in relatedArticles"
            :key="relatedArticle.id"
            class="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm border border-gray-200 dark:border-gray-700 hover:shadow-md transition-shadow cursor-pointer"
            @click="$router.push(`/article/${relatedArticle.id}`)"
          >
            <span
              class="px-2 py-1 bg-primary-100 dark:bg-primary-900 text-primary-700 dark:text-primary-300 text-xs font-medium rounded mb-3 inline-block"
            >
              {{ relatedArticle.category }}
            </span>
            <h3 class="text-lg font-semibold text-gray-900 dark:text-white mb-2 line-clamp-2">
              {{ relatedArticle.title }}
            </h3>
            <p class="text-gray-600 dark:text-gray-300 text-sm line-clamp-2">
              {{ relatedArticle.excerpt }}
            </p>
          </div>
        </div>
      </div>

      <!-- Loading State -->
      <div
        v-if="loading"
        class="text-center py-12"
      >
        <el-icon
          class="text-gray-400 dark:text-gray-500 mb-4"
          size="48"
        >
          <Loading />
        </el-icon>
        <p class="text-gray-500 dark:text-gray-400">
          加载中...
        </p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useBlogStore } from '@/stores/useBlogStore';
import { storeToRefs } from 'pinia';
import { computed, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { ElMessage } from 'element-plus';

const route = useRoute();
const blogStore = useBlogStore();
const { articles } = storeToRefs(blogStore);

const article = ref(null);
const loading = ref(true);

onMounted(async () => {
  const articleId = route.params.id;
  article.value = blogStore.getArticleById(articleId);
  loading.value = false;
});

const relatedArticles = computed(() => {
  if (!article.value) return [];
  return articles.value
    .filter((a) => a.id !== article.value.id && a.category === article.value.category)
    .slice(0, 2);
});

const formatDate = (dateString) => {
  return new Date(dateString).toLocaleDateString('zh-CN', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  });
};

const handleLike = () => {
  ElMessage.success('感谢您的点赞！');
};

const handleBookmark = () => {
  ElMessage.success('文章已收藏！');
};
</script>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.prose {
  color: #374151; /* 亮色模式下的文本颜色 */
}

.dark .prose {
  color: #e5e7eb; /* 暗黑模式下的文本颜色 */
}

.prose :where(h1, h2, h3, h4, h5, h6):not(:where([class~='not-prose'] *)) {
  color: inherit;
  margin-top: 2em;
  margin-bottom: 1em;
}

.prose :where(p):not(:where([class~='not-prose'] *)) {
  margin-top: 1.25em;
  margin-bottom: 1.25em;
  color: inherit;
}

.prose :where(pre):not(:where([class~='not-prose'] *)) {
  background-color: #f8f9fa;
  padding: 1rem;
  border-radius: 0.375rem;
  margin: 1.5em 0;
  overflow-x: auto;
}

.dark .prose :where(pre):not(:where([class~='not-prose'] *)) {
  background-color: #1f2937;
}

.prose :where(code):not(:where([class~='not-prose'] *)) {
  color: #1f2937;
  background-color: #f3f4f6;
  padding: 0.125rem 0.25rem;
  border-radius: 0.25rem;
  font-size: 0.875em;
}

.dark .prose :where(code):not(:where([class~='not-prose'] *)) {
  color: #e5e7eb;
  background-color: #374151;
}

.prose :where(blockquote):not(:where([class~='not-prose'] *)) {
  border-left-color: rgb(59 130 246);
  background-color: rgb(239 246 255);
  padding: 1rem;
  border-radius: 0.375rem;
  margin: 1.5em 0;
}

.dark .prose :where(blockquote):not(:where([class~='not-prose'] *)) {
  background-color: rgb(30 58 138 / 0.2);
  border-left-color: rgb(96 165 250);
  color: #e5e7eb;
}
</style>
