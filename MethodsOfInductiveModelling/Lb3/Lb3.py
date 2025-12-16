import os
import numpy as np
import pandas as pd
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt

DATA_PATH = "./Lb3/Lab2_data_4.csv"
TEST_SIZE = 0.30
RANDOM_STATE = 42
MAX_DEGREE = 12
PATIENT = 1
OUT_CSV = "./Lb3/Lb3GMDH_candidates_results.csv"


def infer_xy_from_df(df: pd.DataFrame):
    if df.shape[1] == 2:
        return df.columns[0], df.columns[1]
    for candidate in ('x','X','input','Input','t','time'):
        if candidate in df.columns:
            for ycand in ('y','Y','target','Target','output','Output'):
                if ycand in df.columns:
                    return candidate, ycand
    return df.columns[0], df.columns[1]

def compute_IC(mse_test: float, k: int, n_test: int) -> float:
    if k >= n_test or (1.0 - (k / n_test)) <= 0:
        return float('inf')
    return mse_test / (1.0 - (k / n_test))

if not os.path.exists(DATA_PATH):
    raise FileNotFoundError(f"Не знайдено файл даних за шляхом: {DATA_PATH}")

df = pd.read_csv(DATA_PATH)
x_col, y_col = infer_xy_from_df(df)

X = df[[x_col]].values.astype(float)
y = df[y_col].values.astype(float)

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=TEST_SIZE, random_state=RANDOM_STATE
)
n_test = len(y_test)

results = []
best_ic = float('inf')
best_degree = None
best_model = None
best_poly = None
no_improve_count = 0

max_deg = min(MAX_DEGREE, max(1, len(X_train) - 1))

for deg in range(1, max_deg + 1):
    poly = PolynomialFeatures(degree=deg, include_bias=False)
    Xtr = poly.fit_transform(X_train)
    Xte = poly.transform(X_test)
    
    model = LinearRegression(fit_intercept=True)
    model.fit(Xtr, y_train)
    
    ytr_pred = model.predict(Xtr)
    yte_pred = model.predict(Xte)
    
    mse_train = mean_squared_error(y_train, ytr_pred)
    mse_test = mean_squared_error(y_test, yte_pred)
    
    k = Xtr.shape[1] + 1
    
    ic = compute_IC(mse_test, k, n_test)
    
    results.append({
        "degree": deg,
        "k_params": k,
        "mse_train": mse_train,
        "mse_test": mse_test,
        "IC": ic,
        "r2_train": r2_score(y_train, ytr_pred),
        "r2_test": r2_score(y_test, yte_pred)
    })
    
    if ic < best_ic:
        best_ic = ic
        best_degree = deg
        best_model = model
        best_poly = poly
        no_improve_count = 0
    else:
        no_improve_count += 1
        if no_improve_count >= PATIENT:
            break

results_df = pd.DataFrame(results)
results_df.to_csv(OUT_CSV, index=False)

if best_model is None:
    raise RuntimeError("Не знайдено придатної моделі GMDH (best_model is None)")

Xtr_full = best_poly.transform(X_train)
Xte_full = best_poly.transform(X_test)
ytr_pred = best_model.predict(Xtr_full)
yte_pred = best_model.predict(Xte_full)

final_metrics = {
    "degree": best_poly.degree,
    "k_params": Xtr_full.shape[1] + 1,
    "mse_train": mean_squared_error(y_train, ytr_pred),
    "mse_test": mean_squared_error(y_test, yte_pred),
    "r2_train": r2_score(y_train, ytr_pred),
    "r2_test": r2_score(y_test, yte_pred),
    "IC": compute_IC(mean_squared_error(y_test, yte_pred), Xtr_full.shape[1] + 1, n_test)
}

print("=== GMDH-подібний відбір (поліноми) ===")
print(f"Файл даних: {DATA_PATH}")
print(f"Колонки: x='{x_col}', y='{y_col}'")
print(f"Train size: {len(y_train)}, Test size: {n_test}")
print("\nКандидати (збережено в):", OUT_CSV)
print(results_df.to_string(index=False))
print("\nФінальна модель (за IC):")
for k, v in final_metrics.items():
    if isinstance(v, float):
        print(f"  {k}: {v:.6g}")
    else:
        print(f"  {k}: {v}")

intercept = float(best_model.intercept_)
coefs = best_model.coef_.ravel().tolist()
print("\nКоефіцієнти фінальної моделі:")
print(f"  intercept: {intercept:.12g}")
for i, c in enumerate(coefs, start=1):
    print(f"  coef x^{i}: {c:.12g}")

x_min, x_max = X.min(), X.max()
x_plot = np.linspace(x_min, x_max, 800).reshape(-1,1)
y_plot = best_model.predict(best_poly.transform(x_plot))

plt.figure(figsize=(9,6))
plt.scatter(X_train, y_train, label="train", s=20, alpha=0.7)
plt.scatter(X_test, y_test, label="test", s=20, alpha=0.9, marker='x')
plt.plot(x_plot, y_plot, label=f"GMDH model (deg={best_poly.degree})", linewidth=2)
plt.title("GMDH-подібна поліноміальна модель")
plt.xlabel(x_col)
plt.ylabel(y_col)
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()
