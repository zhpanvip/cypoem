package com.zhpan.library.base.mvc.activity;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * activity列表基类,封装刷新和加载更多
 */

public abstract class BaseVcListActivity extends BaseVcActivity implements OnRefreshListener, OnLoadMoreListener {

    protected int page = 0;
    protected int pageSize = 10;
    protected boolean isRefresh = true;
    protected SmartRefreshLayout rlRefreshLayout;

    @Override
    protected void initView() {
        if (rlRefreshLayout != null) {
            rlRefreshLayout.setOnRefreshListener(this);
            rlRefreshLayout.setOnLoadMoreListener(this);
            rlRefreshLayout.autoRefresh();
        }
    }

    /**
     * 刷新
     * @param refreshLayout
     */
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        this.page = 0;
        isRefresh = true ;
        loadListData(rlRefreshLayout ,page, pageSize);
    }

    /**
     * 加载更多
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        isRefresh = false ;
        loadListData(rlRefreshLayout ,page, pageSize);
    }

    public abstract void loadListData(SmartRefreshLayout rlRefreshLayout , int page, int pageSize);

}
