# CloudStudio 部署指南

## 项目概述
这是一个基于 Vue 3 + Element Plus + Tailwind CSS 的个人博客项目，包含以下功能：
- 响应式设计
- 暗色/亮色主题切换
- 文章分类和展示
- 技术分享和作品展示

## 部署到 CloudStudio

### 方法一：直接导入项目
1. 打开 CloudStudio 工作空间
2. 选择「导入项目」
3. 上传整个 `blog` 文件夹
4. 等待依赖安装完成

### 方法二：Git 仓库导入
1. 将项目推送到 GitHub/GitLab
2. 在 CloudStudio 中选择「从 Git 仓库导入」
3. 输入仓库地址
4. 等待克隆和依赖安装

### 启动开发服务器
```bash
cd blog
npm install
npm run dev
```

### 构建生产版本
```bash
npm run build
```

## 项目结构
```
blog/
├── src/
│   ├── components/     # Vue 组件
│   ├── views/         # 页面组件
│   ├── stores/        # Pinia 状态管理
│   ├── router/        # 路由配置
│   └── main.js        # 入口文件
├── public/            # 静态资源
├── index.html         # HTML 模板
├── vite.config.js     # Vite 配置
├── tailwind.config.js # Tailwind 配置
└── postcss.config.js  # PostCSS 配置
```

## 环境要求
- Node.js 18+
- npm 或 yarn
- 现代浏览器支持

## 访问地址
开发服务器启动后访问：http://localhost:5175/
生产构建后文件在 `dist/` 目录

## 注意事项
1. Tailwind CSS v4 需要特殊的 PostCSS 配置
2. 确保所有依赖正确安装
3. 端口可能被占用，会自动选择其他端口