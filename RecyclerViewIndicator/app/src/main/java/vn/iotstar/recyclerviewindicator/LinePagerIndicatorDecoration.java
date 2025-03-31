package vn.iotstar.recyclerviewindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {
    private final int colorActive = 0xFFFFFFFF;
    private final int colorInactive = 0x66FFFFFF;

    private final float DP;
    private final Paint paint = new Paint();

    public LinePagerIndicatorDecoration(float density) {
        this.DP = density;
        paint.setAntiAlias(true);
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int itemCount = parent.getAdapter() != null ? parent.getAdapter().getItemCount() : 0;
        if (itemCount == 0) return;

        float indicatorWidth = DP * 8 * itemCount;
        float indicatorStartX = (parent.getWidth() - indicatorWidth) / 2;
        float indicatorPosY = parent.getHeight() - DP * 16;

        drawInactiveDots(canvas, indicatorStartX, indicatorPosY, itemCount);
        drawActiveDot(canvas, indicatorStartX, indicatorPosY, parent);
    }

    private void drawInactiveDots(Canvas canvas, float startX, float posY, int itemCount) {
        float itemWidth = DP * 8;
        for (int i = 0; i < itemCount; i++) {
            paint.setColor(colorInactive);
            canvas.drawCircle(startX + itemWidth * i, posY, DP * 3, paint);
        }
    }

    private void drawActiveDot(Canvas canvas, float startX, float posY, RecyclerView recyclerView) {
        int activePosition = ((RecyclerView.LayoutParams) recyclerView.getChildAt(0).getLayoutParams()).getViewAdapterPosition();
        float highlightPosition = startX + DP * 8 * activePosition;
        paint.setColor(colorActive);
        canvas.drawCircle(highlightPosition, posY, DP * 3, paint);
    }
}

