import argparse
import numpy as np
import matplotlib.pyplot as plt
import csv
import os


def generate_data(n: float, N: int = 100, x_min: float = -10.0, x_max: float = 10.0,
                  noise_std: float = 1.0, seed: int | None = 42):
    rng = np.random.default_rng(seed)
    x = np.linspace(x_min, x_max, N)
    noise = rng.normal(loc=0.0, scale=noise_std, size=N)
    y = n * x + np.sin(x / n) + noise
    return x, y


def train_test_split(x: np.ndarray, y: np.ndarray, train_frac: float = 0.7, seed: int | None = 42):
    N = x.shape[0]
    rng = np.random.default_rng(seed)
    perm = rng.permutation(N)
    train_size = int(np.floor(train_frac * N))
    train_idx = perm[:train_size]
    test_idx = perm[train_size:]
    return x[train_idx], x[test_idx], y[train_idx], y[test_idx]


def fit_linear_regression(x_train: np.ndarray, y_train: np.ndarray):
    # Поліном ступеня 1: повертає [a, b] для a*x + b
    a, b = np.polyfit(x_train, y_train, 1)
    return float(a), float(b)


def predict(a: float, b: float, x: np.ndarray):
    return a * x + b


def evaluate(y_true: np.ndarray, y_pred: np.ndarray):
    mse = float(np.mean((y_true - y_pred) ** 2))
    mae = float(np.mean(np.abs(y_true - y_pred)))
    sse = float(np.sum((y_true - y_pred) ** 2))
    sst = float(np.sum((y_true - np.mean(y_true)) ** 2))
    r2 = 1.0 - sse / sst if sst != 0 else float('nan')
    return mse, mae, r2


def save_metrics_to_csv(metrics: dict, filename: str = 'metrics.csv'):
    with open(filename, 'w', newline='', encoding='utf-8') as f:
        writer = csv.writer(f)
        writer.writerow(['Метрика', 'Значення'])
        for k, v in metrics.items():
            writer.writerow([k, v])


def plot_results(x_train, y_train, x_test, y_test, a, b, filename='regression_plot.png'):
    x_line = np.linspace(min(np.min(x_train), np.min(x_test)),
                         max(np.max(x_train), np.max(x_test)), 400)
    y_line = a * x_line + b
    plt.figure(figsize=(10, 7))
    plt.scatter(x_train, y_train, label='Навчальні точки', marker='o', alpha=0.8)
    plt.scatter(x_test, y_test, label='Тестові точки', marker='s', alpha=0.9)
    plt.plot(x_line, y_line, label=f'Лінійна регресія: y = {a:.4f}x + {b:.4f}', linewidth=2)
    plt.xlabel('x')
    plt.ylabel('y')
    plt.title('Лінійна регресія на синтетичних даних')
    plt.legend()
    plt.grid(True, linestyle='--', alpha=0.6)
    plt.tight_layout()
    plt.savefig(filename, dpi=200)
    print(f"Графік збережено у файл: {os.path.abspath(filename)}")
    plt.show()


def main():
    parser = argparse.ArgumentParser(description='Синтетична лінійна регресія')
    parser.add_argument('--n', type=float, default=9.0, help='Номер варіанту (параметр n в формулі)')
    parser.add_argument('--N', type=int, default=100, help='Кількість точок')
    parser.add_argument('--seed', type=int, default=42, help='Seed для генератора випадкових чисел')
    parser.add_argument('--noise', type=float, default=1.0, help='Стандартне відхилення шуму')
    parser.add_argument('--train_frac', type=float, default=0.7, help='Частка для навчальної вибірки')
    args = parser.parse_args()
    # Генерація даних
    x, y = generate_data(n=args.n, N=args.N, noise_std=args.noise, seed=args.seed)
    # Розбиття
    x_train, x_test, y_train, y_test = train_test_split(x, y, train_frac=args.train_frac, seed=args.seed)
    # Навчання
    a, b = fit_linear_regression(x_train, y_train)
    # Прогнози
    y_pred_train = predict(a, b, x_train)
    y_pred_test = predict(a, b, x_test)
    # Оцінка
    mse_train, mae_train, r2_train = evaluate(y_train, y_pred_train)
    mse_test, mae_test, r2_test = evaluate(y_test, y_pred_test)
    # Вивід
    print('\n=== РЕЗУЛЬТАТИ ===')
    print(f'Варіант n = {args.n}')
    print(f'Коефіцієнти: a = {a:.6f}, b = {b:.6f}')
    print('\n-- Навчальна вибірка --')
    print(f' MSE = {mse_train:.6f}')
    print(f' MAE = {mae_train:.6f}')
    print(f' R^2 = {r2_train:.6f}')
    print('\n-- Тестова вибірка --')
    print(f' MSE = {mse_test:.6f}')
    print(f' MAE = {mae_test:.6f}')
    print(f' R^2 = {r2_test:.6f}')
    metrics = {
        'MSE_train': mse_train,
        'MAE_train': mae_train,
        'R2_train': r2_train,
        'MSE_test': mse_test,
        'MAE_test': mae_test,
        'R2_test': r2_test,
    }
    save_metrics_to_csv(metrics, filename='metrics.csv')
    print(f"Метрики збережено у файл: {os.path.abspath('metrics.csv')}")
    # Плот
    plot_results(x_train, y_train, x_test, y_test, a, b, filename='regression_plot.png')


if __name__ == '__main__':
    main()
