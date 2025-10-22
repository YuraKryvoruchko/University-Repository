from __future__ import annotations
import os
import re
import sys
import numpy as np
import matplotlib.pyplot as plt
import tkinter as tk
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures
from sklearn.pipeline import make_pipeline
from sklearn.model_selection import train_test_split, cross_val_score, KFold
from sklearn.metrics import mean_squared_error, r2_score
from pathlib import Path
from typing import List, Tuple
from tkinter import filedialog, messagebox


def safe_float(s: str) -> float:
    """Очистити рядок і спробувати конвертувати у float. Повертає np.nan при помилці."""
    if s is None:
        return np.nan
    s = str(s).strip()
    if s == '':
        return np.nan
    s = s.replace(',', '.')  # десяткова кома -> крапка
    s = re.sub(r'[^0-9\.\-eE\+]', '', s)
    if s in ('', '.', '+', '-', 'e', 'E'):
        return np.nan
    try:
        return float(s)
    except Exception:
        return np.nan


def parse_table_file(path: str) -> Tuple[np.ndarray, np.ndarray]:
    """Зчитує файл path, повертає X (n,1) та y (n,)"""
    xs: List[float] = []
    ys: List[float] = []
    with open(path, 'r', encoding='utf-8', errors='ignore') as f:
        lines = f.readlines()

    for raw in lines:
        line = raw.strip()
        if not line:
            continue
        # спробуємо розбити по ';' якщо є, інакше по пробілу/комі/таб
        if ';' in line:
            parts = [p.strip() for p in line.split(';') if p.strip() != '']
        else:
            parts = re.split(r'[\s,;]+', line)
            parts = [p.strip() for p in parts if p.strip() != '']

        nums = [safe_float(p) for p in parts]
        nums = [n for n in nums if not np.isnan(n)]
        if len(nums) >= 2:
            x_val = nums[-2]
            y_val = nums[-1]
            xs.append(x_val)
            ys.append(y_val)
        else:
            # ігноруємо рядки без двох чисел
            continue

    if len(xs) == 0:
        raise ValueError(f"Не знайдено числових пар у файлі: {path}")

    X = np.array(xs).reshape(-1, 1)
    y = np.array(ys)
    return X, y


def evaluate_models(X, y, degrees: List[int], show_plot: bool = True, out_plot: str | None = None):
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
    results = []
    print("\nМоделі та метрики (MSE, R2, MSE_cv):")
    header = f"{'deg':>3s} | {'MSE_train':>12s} | {'MSE_test':>12s} | {'R2_train':>8s} | {'R2_test':>8s} | {'MSE_cv':>12s}"
    print(header)
    print('-' * len(header))
    for d in degrees:
        model = make_pipeline(PolynomialFeatures(degree=d, include_bias=True), LinearRegression())
        model.fit(X_train, y_train)
        y_pred_train = model.predict(X_train)
        y_pred_test = model.predict(X_test)
        mse_train = mean_squared_error(y_train, y_pred_train)
        mse_test = mean_squared_error(y_test, y_pred_test)
        r2_tr = r2_score(y_train, y_pred_train)
        r2_te = r2_score(y_test, y_pred_test)
        kf = KFold(n_splits=5, shuffle=True, random_state=42)
        neg_mse_cv = cross_val_score(model, X, y, scoring='neg_mean_squared_error', cv=kf)
        mse_cv = -np.mean(neg_mse_cv)
        results.append((d, mse_train, mse_test, r2_tr, r2_te, mse_cv))
        print(f"{d:3d} | {mse_train:12.6e} | {mse_test:12.6e} | {r2_tr:8.4f} | {r2_te:8.4f} | {mse_cv:12.6e}")

    # графік
    x_plot = np.linspace(X.min(), X.max(), 800).reshape(-1, 1)
    plt.figure(figsize=(10, 6))
    plt.scatter(X, y, label='data', s=20)
    for d in degrees:
        model = make_pipeline(PolynomialFeatures(degree=d, include_bias=True), LinearRegression())
        model.fit(X_train, y_train)
        y_plot = model.predict(x_plot)
        plt.plot(x_plot, y_plot, label=f'degree={d}')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.title('Поліноміальна регресія: порівняння ступенів')
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    if out_plot:
        plt.savefig(out_plot)
        print(f"Графік збережено у: {out_plot}")
    if show_plot:
        plt.show()
    plt.close()

    best = min(results, key=lambda r: r[5])
    print(f"\nНайменше MSE_cv при ступені {best[0]} (MSE_cv={best[5]:.6e})")
    print("Порада: якщо MSE_train << MSE_test для великого d — це ознака перенавчання.")


def main():
    # очікуване ім'я файлу
    expected = "Lab2_data_4.csv"
    script_dir = None
    try:
        script_dir = Path(__file__).resolve().parent
    except Exception:
        script_dir = Path.cwd()

    # перевіряємо кілька місць
    candidates = [
        Path.cwd() / expected,              # поточна робоча тека (Run -> Current working directory in Thonny)
        script_dir / expected,              # та тека, де збережено скрипт
    ]
    found = None
    for p in candidates:
        if p.exists():
            found = str(p)
            break

    print(f"Використовую файл: {found}")
    try:
        X, y = parse_table_file(found)
    except Exception as e:
        print("Помилка при зчитуванні файлу:", e)
        if messagebox:
            root = tk.Tk(); root.withdraw()
            messagebox.showerror("Помилка читання", f"Не вдалося розібрати файл:\n{e}")
            root.destroy()
        sys.exit(2)

    degrees = [1, 2, 3, 6, 12]
    evaluate_models(X, y, degrees, show_plot=True, out_plot=None)


if __name__ == "__main__":
    main()
